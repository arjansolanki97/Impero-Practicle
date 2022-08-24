package com.hummingtech.imperopracticla.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductModel {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PriceCode")
    @Expose
    private String priceCode;
    @SerializedName("ImageName")
    @Expose
    private String imageName;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
