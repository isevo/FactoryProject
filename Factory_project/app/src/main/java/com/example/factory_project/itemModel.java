package com.example.factory_project;

/**
 * klasa u kojoj se spremju podaci o svim elementima json objekta
 */

public class itemModel {
    /**
     * varijabla tipa string u kojoj se sprema opis
     */
    String description;
    /**
     * varijabla tipa string u kojoj se sprema naslov
     */
    String title;
    /**
     * varijabla tipa string u kojoj se sprema url
     */
    String url;
    /**
     * varijabla tipa string u kojoj se sprema url slike
     */
    String urlToImage;

    /**
     * metoda koja dohvaca opis
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * metoda koja dohvaca naslov
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * metoda koja dohvaca url
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * metoda koja dohvaca url slike
     * @return urlToImage
     */
    public String getUrlToImage() {
        return urlToImage;
    }

    /**
     * metoda kojom se postavlja opis
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * metoda kojom se postavlja naslov
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * metoda kojom se postavlja url
     * @param url
     */

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * metoda kojom se postvlja url slike
     * @param urlToImage
     */
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    /**
     * metoda koja omogucuje ispis
     * @return
     */

    @Override
    public String toString() {
        return "itemModel{" +
                "url='" + url + '\'' +
                '}';
    }
}
