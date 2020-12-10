package com.example.factory_project;

import java.util.ArrayList;
import java.util.List;

/**
 * klasa odgovorna za dohvacanje liste i odgovora
 */
public class ResponseModel {
    /**
     * varijabla tipa string koja sprema status
     */
    private String status;

    private int totalResults;
    /**
     * lista objekata
     */
    private ArrayList<itemModel> articles = null;

    /**
     * metoda za dohvacanje statusa
     * @return status
     */

    public String getStatus() {
        return status;
    }

    /**
     * metoda kojom se postavlja status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * dohvacanje liste
     * @return articles
     */

    public ArrayList<itemModel> getArticles() {

        return articles;
    }

    /**
     * postavlje nove vrijednosti za listu
     * @param articles
     */
    public void setArticles(ArrayList<itemModel> articles) {
        this.articles = articles;
    }










}
