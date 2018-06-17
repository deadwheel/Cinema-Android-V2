package com.wfis.wfis_shop.models;

/**
 * Created by Denis on 2018-05-21.
 */

public class News {

    String title;
    String content;
    String data;
    String image;


    public News() {}

    public News(String title, String content, String data, String image) {
        this.title = title;
        this.content = content;
        this.data = data;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
