package com.wfis.wfis_shop.models;

/**
 * Created by Denis on 2018-05-28.
 */

public class ResNearbyDetail {

    String name;
    String vicinity;

    public ResNearbyDetail() {}

    public ResNearbyDetail(String name, String vicinity) {
        this.name = name;
        this.vicinity = vicinity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
