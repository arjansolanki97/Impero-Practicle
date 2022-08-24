package com.hummingtech.imperopracticla.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultModel {

    @SerializedName("Category")
    @Expose
    private List<CategoryModel> category = null;

    public List<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.category = category;
    }
}
