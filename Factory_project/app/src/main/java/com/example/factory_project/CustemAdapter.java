package com.example.factory_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 *
 */
public class CustemAdapter extends BaseAdapter {

    /**
     * objkt tipa Context (U ovoj aplikaciji je uvijek proslijedivan MainActivity)
     */
    Context context;
    /**
     * lista objekata itemModel
     */
    ArrayList<itemModel> object_array;
   // ArrayList<Article>articles;

    /**
     * konstruktor
     * @param mainActivity
     * @param arrayList u ovoj listi su spremljeni podaci o svim objektima iz liste iz json-a
     */
    public CustemAdapter(MainActivity mainActivity, ArrayList<itemModel> arrayList) {
        this.context=mainActivity;
        this.object_array=arrayList;

    }

  /*  public CustemAdapter(Context context, ArrayList<Article> lista) {
        this.context = context;
        this.articles=lista;
    }*/


    /**
     * dohvaca duljinu liste
     * @return duljina liste
     */
    @Override
    public int getCount() {
        return object_array.size();
    }

    /**
     * dohvaca pojedini elemnt liste
     * @param position varijabla tipa int- pozicija elemnta u listi
     * @return elemnt na odredenoj poziciji
     */
    @Override
    public Object getItem(int position) {
        return object_array.get(position);

    }

    /**
     * dohvacanje pozicije elemnta u listi
     * @param position vracanje pozicije
     * @return position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * metoda kojom se postavljaju slike i tekst na listView
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }
        ImageView image;
        TextView title;
        title=(TextView)convertView.findViewById(R.id.title);
        image=(ImageView)convertView.findViewById(R.id.imageView);

        System.out.println(object_array.get(position).getUrlToImage());


        title.setText(object_array.get(position).getTitle());
        Picasso.with(context).load(object_array.get(position).getUrlToImage()).fit().placeholder(R.drawable.ic_launcher_background).into(image);



        return convertView;


    }
}
