package com.wfis.wfis_shop.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 2018-05-28.
 */

public class ResNearby {


    String status;
    List<ResNearbyDetail> results = new ArrayList<>();

    public ResNearby() {}

    public ResNearby(String status, List<ResNearbyDetail> results) {
        this.status = status;
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResNearbyDetail> getResults() {
        return results;
    }

    public void setResults(List<ResNearbyDetail> results) {
        this.results = results;
    }
}
