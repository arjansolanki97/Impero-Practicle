package com.hummingtech.imperopracticla.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryModel {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("IsAuthorize")
    @Expose
    private Integer isAuthorize;
    @SerializedName("Update080819")
    @Expose
    private Integer update080819;
    @SerializedName("Update130919")
    @Expose
    private Integer update130919;
    @SerializedName("SubCategories")
    @Expose
    private List<SubCategoryModel> subCategories = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsAuthorize() {
        return isAuthorize;
    }

    public void setIsAuthorize(Integer isAuthorize) {
        this.isAuthorize = isAuthorize;
    }

    public Integer getUpdate080819() {
        return update080819;
    }

    public void setUpdate080819(Integer update080819) {
        this.update080819 = update080819;
    }

    public Integer getUpdate130919() {
        return update130919;
    }

    public void setUpdate130919(Integer update130919) {
        this.update130919 = update130919;
    }

    public List<SubCategoryModel> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryModel> subCategories) {
        this.subCategories = subCategories;
    }

}
