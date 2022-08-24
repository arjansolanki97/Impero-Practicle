package com.hummingtech.imperopracticla.models;

public class CategoryRequestModel {

    int CategoryId;
    int PageIndex;

    public CategoryRequestModel() {
    }

    public CategoryRequestModel(int categoryId, int pageIndex) {
        CategoryId = categoryId;
        PageIndex = pageIndex;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }
}
