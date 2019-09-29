package com.example.mdbpurchases2;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    String cost;
    String description;
    String supplier;
    String photoURL;
    Date date;

    public Purchase(String a, String b, String c, String d, Date e) {
        cost = a;
        description = b;
        supplier = c;
        photoURL = d;
        date = e;
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

    public Date getDate() {
        return date;
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

    public void setDate(Date date) {
        this.date = date;
    }
}
