package com.example.factory_project;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIInterface {

    /**
     * metoda u kojoj se šalje url adresa i dobiva se odgovor
     */
    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("sources") String source, @Query("apiKey") String apiKey);


}
