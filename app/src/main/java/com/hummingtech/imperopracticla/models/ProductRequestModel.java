package com.hummingtech.imperopracticla.models;

public class ProductRequestModel {

    int SubCategoryId;
    int PageIndex;

    public ProductRequestModel() {
    }

    public ProductRequestModel(int SubCategoryId, int pageIndex) {
        this.SubCategoryId = SubCategoryId;
        this.PageIndex = pageIndex;
    }

    public int getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }
}
