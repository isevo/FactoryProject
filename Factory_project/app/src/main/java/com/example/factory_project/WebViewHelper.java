package com.example.factory_project;

import android.telecom.Call;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.security.auth.callback.Callback;

public class WebViewHelper {
    /**
     * Objekt tipa WebView na kojem ce se prikazati stranica
     */
   private WebView web;

    /**
     * konstruktor
     * @param web
     */
    public WebViewHelper(WebView web) {
        this.web=web;

    }

    /**
     * metoda koja omogucava ucitavanje url-a
     * @param url
     */
    public void Set_web(String url) {
        WebSettings webSettings=this.web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());

        web.loadUrl(url);

    }

    /**
     * omogucava de se unutar otvorene stranice otvori jos neka klikom na link
     */
    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;


        }
    }
}
