package com.wfis.wfis_shop.models;

import com.orm.SugarRecord;

/**
 * Created by Denis on 2018-05-16.
 */

public class Movies extends SugarRecord<Movies> {

    String title;
    String dateProd;
    String prod;
    String imageMov;
    boolean isSpectate;

    public Movies() {}

    public Movies(String title, String dateProd, String prod, String imageMov, boolean isSpectate) {
        this.title = title;
        this.dateProd = dateProd;
        this.prod = prod;
        this.imageMov = imageMov;
        this.isSpectate = isSpectate;
    }

    public String getImageMov() {
        return imageMov;
    }

    public void setImageMov(String imageMov) {
        this.imageMov = imageMov;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateProd() {
        return dateProd;
    }

    public void setDateProd(String dateProd) {
        this.dateProd = dateProd;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public boolean isSpectate() {
        return isSpectate;
    }

    public void setSpectate(boolean spectate) {
        isSpectate = spectate;
    }
}
