package com.hummingtech.imperopracticla.retrofit;

import com.hummingtech.imperopracticla.models.CategoryRequestModel;
import com.hummingtech.imperopracticla.models.ProductModel;
import com.hummingtech.imperopracticla.models.ProductRequestModel;
import com.hummingtech.imperopracticla.models.ResponseModel;
import com.hummingtech.imperopracticla.models.ResultModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("DashBoard")
    Call<ResponseModel<ResultModel>> callDashboard(
            @Body CategoryRequestModel categoryRequestModel
    );

    @POST("ProductList")
    Call<ResponseModel<List<ProductModel>>> callProductList(
            @Body ProductRequestModel productRequestModel
    );

}

