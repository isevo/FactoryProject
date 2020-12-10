package com.example.factory_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * ova metoda prima url iz MainActivity i omogućava da se svaki url sa prvog slajda prenese na idući
 */
public class DetailActivity extends AppCompatActivity {
    /**
     * objekt tipa WebViewHelper
     */
    WebViewHelper wvh;
    /**
     * Objekt tipa WebView
     */
    WebView web;


    /**
     * metoda koja prima url sa prvog slajda iz MainActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //kreiranje objekta web
        web=(WebView)findViewById(R.id.webView1);
         //Intent intent=getIntent();

        //dohvacanje proslijedenog url-a
        String text= getIntent().getExtras().getString(" com.example.NewItem");
        //System.out.println("=============================== "+text.getClass().getSimpleName()+" =============================");
        wvh=new WebViewHelper(web);
        wvh.Set_web(text);


    }
}