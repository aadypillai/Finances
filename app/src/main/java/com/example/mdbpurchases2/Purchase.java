package com.example.mdbpurchases2;

import android.util.Log;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Purchase implements Serializable {
    String cost;
    String description;
    String supplier;
    String photoURL;
    Long timestamp;

    public Purchase() {

    }

    public Purchase(String a, String b, String c, String d, Long e) {
        cost = a;
        description = b;
        supplier = c;
        photoURL = d;
        timestamp = e;
    }
    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
