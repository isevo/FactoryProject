package com.example.factory_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ivana sevo
 */

public class MainActivity extends AppCompatActivity {
    /**
     * Objekt klase ListView koji sadržava arrayList,korišten je za ispis podataka u obliku kartica
     */
    ListView listView;
    /**
     * lista objekata itemModel u kojem su spremljeni svi podaci sa jsona(description,title,url...)
     */
    ArrayList<itemModel>arrayList;
    /**
     * objekt klase Handler koje je omogućio ažuriranje podataka
     */
    Handler handler = new Handler();

    /**
     * objekt klase Runnable
     */Runnable runnable;
    /**
     * varijabla koja odreduje nakon koliko vremena ce slati request
     */
    int delay = 15000;
    /**
     * Objekt klase Context
     */
    private Context mContext;


    /**
     * api key korišten za dohvaćanje api-a
     */

    private static final String API_KEY = "3782be906527468aa05d01858bdf3ea1";
    /**
     * varijabla koja dohvaća ime klase
     */

    private String TAG=MainActivity.class.getSimpleName();

    /**
     * u ovoj metodi je provjeren i dohvacen json file koji je spremljen lokalno,te je postavljen lisener za listView
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listView1);

        arrayList=new ArrayList<>();



        mContext = getApplicationContext();




        //spiner.setVisibility(View.GONE);
        String json=readJson();
        if (json!=null) {


            try {
                //kada se procita .json ,spremi se u json object


                JSONObject object = new JSONObject(json);

                //ovaj objekt sadrži niz objekata
                JSONArray array = object.getJSONArray("articles");

                GetObjects(array);


            } catch (JSONException e) {
                System.out.println("============================================================================================");
                e.printStackTrace();
            }


            CustemAdapter customerAdapter = new CustemAdapter(this, arrayList);
            listView.setAdapter(customerAdapter);


            //kada se klikne na neki elemnt liste

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                    Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                    //prosljedujemo vrijedenost na drugi slajd

                    showDetailActivity.putExtra(" com.example.NewItem", arrayList.get(position).getUrl());
                    startActivity(showDetailActivity);
                  //  spiner.setVisibility(View.GONE);
                }
            });


        }
        else{

                ReadFromUrl();

        }



    }



    //da se kod ponavlja dio koda

    /**
     * u ovoj metodi je definirano nakon koliko vremena će se slati request,poziva se automatski prilikom pokretanja aplikacije
     */
    //@Override
   protected void onResume() {


        handler.postDelayed(runnable =new Runnable() {
            @Override
            public void run() {
                // arrayList.clear();
                handler.postDelayed(runnable,delay);

                ReadFromUrl();


                Toast.makeText(MainActivity.this, "Ovo ce se ponoviti za 60 sec", Toast.LENGTH_SHORT).show();
            }
        },delay);



        super.onResume();
    }


    /**
     * u ovoj metodi se dohvaca json sa url-a
     */
    public void ReadFromUrl(){


            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);



                final APIInterface apiService = APIClient.getClient().create(APIInterface.class);
                Call<ResponseModel> call = apiService.getLatestNews("bbc-news",API_KEY);
                System.out.println("********************************************************************************************************");
                call.enqueue(new Callback<ResponseModel>() {
                    /**
                     * podaci se prosljeduju adapteru i ddodaje se listener za svaki element ListView-a
                     * @param call sadrzi url api-a
                     * @param response vraca odgovor tj podatke sa json-a
                     */
                    @Override
                    //ovdje ulaze url
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if(response.body().getStatus().equals("ok")) {
                            arrayList.clear();
                            //  List<Article> articleList = response.body().getArticles();
                            ArrayList <itemModel>articleList=response.body().getArticles();


                            //CustemAdapter customerAdapter=new CustemAdapter(MainActivity.this, (ArrayList<Article>) articleList);

                            CustemAdapter custemAdapter=new CustemAdapter(MainActivity.this,articleList);
                            listView.setAdapter(custemAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                /**
                                 * klikom na element liste proslijeduje se podatak na drugi slajd
                                 * @param parent
                                 * @param view
                                 * @param position dohvaca poziciju svakog elemnta iz liste
                                 * @param id dohvaca id svakog elementa iz liste
                                 */

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                                    //prosljeđujemo vrijedenost na drugi slajd

                                    showDetailActivity.putExtra(" com.example.NewItem", articleList.get(position).getUrl());
                                    startActivity(showDetailActivity);

                                }
                            });



                            if(articleList.size()>0) {
                            }
                        }
                    }

                    /**
                     * u slucaju da request nije dobar poziva se PopUpMessage() i pojavljuje se poruka
                     * @param call url koji se dohvaca
                     * @param t opis greške
                     */
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                     /*   Tools.exceptionToast(getApplicationContext(), t.getMessage());
                        Log.e("out", t.toString());*/
                        PopUpMessage();



                    }


                });


        Toast.makeText(MainActivity.this, "Ovo ce se ponoviti za 60 sec", Toast.LENGTH_SHORT).show();


    }

    /**
     * PopUpMessage- metoda kojom se postavlja Alert dialog
     * klikom na U REDU se izlazi iz alerta
     */
    public void PopUpMessage(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);


        // set title
        alertDialogBuilder.setTitle("GREŠKA");

        // poruka
        alertDialogBuilder
                .setMessage("Ups,došlo je do greške!")
                .setCancelable(false)
                //kad se klikne na botun izađe se iz alert dialoga
                .setNegativeButton("U REDU",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        //stvoriti alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // staviti ga da bude vidljiv
        alertDialog.show();



    }

    /**
     * metoda kojom se dohvacaju podaci sa json-a i spremaju u objekt itemModel
     * ovo se odnosi samo na json koji je lookalno spremljen
     * @param array objekt koji dohvaca sve objekte iz niza u json-u
     */

    public void GetObjects(JSONArray array) {


        for (int i = 0; i < array.length(); i++) {

            System.out.println("------------------------------------------------------------------------------------");

            //dohvaća se svaki objekt niza i sprema ga se u privremenu varijablu
            JSONObject jsonObject = null;
            try {
                jsonObject = array.getJSONObject(i);
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                String url = jsonObject.getString("url");
                String urlToImage = jsonObject.getString("urlToImage");

                System.out.println(title);
                System.out.println("------------------------------------------------------------------------------------");


                //objekt
                itemModel model = new itemModel();
                model.setTitle(title);
                model.setDescription(description);
                model.setUrl(url);
                model.setUrlToImage(urlToImage);

                arrayList.add(model);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

    }

    //metoda koja cita .json

    /**
     * otaranje json fajla
     * @return
     */
    public String readJson() {
        String json = null;
        try {
            //otvaranje .json fajla


            InputStream inputStream = getAssets().open("news.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            //citanje vrijednosti u byte array
            inputStream.read(buffer);
            inputStream.close();

            //pretvoriti byte u String
            json = new String(buffer, "UTF-8");





        } catch (IOException e) {

            e.printStackTrace();

            return json;
        }
        return json;
    }



    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }







}