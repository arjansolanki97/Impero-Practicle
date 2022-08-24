package com.hummingtech.imperopracticla.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.hummingtech.imperopracticla.R;
import com.hummingtech.imperopracticla.adapters.ViewPagerAdapter;
import com.hummingtech.imperopracticla.databinding.ActivityMainBinding;
import com.hummingtech.imperopracticla.dialog.LoadingDialog;
import com.hummingtech.imperopracticla.models.CategoryModel;
import com.hummingtech.imperopracticla.models.CategoryRequestModel;
import com.hummingtech.imperopracticla.models.ResponseModel;
import com.hummingtech.imperopracticla.models.ResultModel;
import com.hummingtech.imperopracticla.retrofit.ApiClient;
import com.hummingtech.imperopracticla.retrofit.RetrofitCallbacks;
import com.hummingtech.imperopracticla.utils.ToastUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
        getDashboard();
    }

    private void init(){

        loadingDialog = new LoadingDialog(this);
    }

    private void getDashboard(){

        loadingDialog.show();

        RetrofitCallbacks<ResponseModel<ResultModel>> callbacks = new RetrofitCallbacks<ResponseModel<ResultModel>>(this, loadingDialog) {
            @Override
            public void onSuccess(Response<ResponseModel<ResultModel>> response) {

                ResponseModel responseModel = response.body();

                if(responseModel!=null) {

                    if (responseModel.getStatus() != null && responseModel.getStatus() == 200) {

                        List<CategoryModel> list = response.body().getResult().getCategory();

                        setViewPager(list);
                    }
                    else {
                        ToastUtils.showErrorToast(getString(R.string.something_went_wrong));
                    }
                }
                else {
                    ToastUtils.showErrorToast(getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFail(@NonNull Call<ResponseModel<ResultModel>> call, @NonNull Throwable t) {

            }
        };

        CategoryRequestModel categoryRequestModel = new CategoryRequestModel(0,1);

        ApiClient.getApiInterface().callDashboard(categoryRequestModel).enqueue(callbacks);
    }

    private void setViewPager(List<CategoryModel> categoryModelList){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), categoryModelList);
        binding.viewpager.setAdapter(viewPagerAdapter);

        //binding.tabLayout.setupWithViewPager(binding.viewpager);
    }
}