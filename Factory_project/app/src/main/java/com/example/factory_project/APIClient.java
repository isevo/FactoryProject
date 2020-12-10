package com.example.factory_project;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * u ovoj klase se provjerava request
 */
public class APIClient {
    /**
     * osnovni dio url-a
     */
    public static final String BASE_URL = "https://newsapi.org/v2/";

    private static Retrofit retrofit = null;




    /**
     * salje se request api-u
     * @return retrofit
     */
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }







}
