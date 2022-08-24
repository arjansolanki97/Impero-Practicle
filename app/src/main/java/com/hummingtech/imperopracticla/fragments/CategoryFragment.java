package com.hummingtech.imperopracticla.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hummingtech.imperopracticla.R;
import com.hummingtech.imperopracticla.adapters.SubCategoryAdapter;
import com.hummingtech.imperopracticla.databinding.FragmentCategoryBinding;
import com.hummingtech.imperopracticla.dialog.LoadingDialog;
import com.hummingtech.imperopracticla.models.CategoryModel;
import com.hummingtech.imperopracticla.models.CategoryRequestModel;
import com.hummingtech.imperopracticla.models.ResponseModel;
import com.hummingtech.imperopracticla.models.ResultModel;
import com.hummingtech.imperopracticla.models.SubCategoryModel;
import com.hummingtech.imperopracticla.retrofit.ApiClient;
import com.hummingtech.imperopracticla.retrofit.RetrofitCallbacks;
import com.hummingtech.imperopracticla.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;
    CategoryModel categoryModel;
    LoadingDialog loadingDialog;

    CategoryRequestModel categoryRequestModel;
    List<SubCategoryModel> subCategoryModelList = new ArrayList<>();
    SubCategoryAdapter subCategoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public CategoryFragment(CategoryModel categoryModel) {
        this.categoryModel=categoryModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        getSubCategory(categoryRequestModel);
    }

    private void init(){

        categoryRequestModel = new CategoryRequestModel(categoryModel.getId(),1);

        loadingDialog = new LoadingDialog(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvSubCategory.setLayoutManager(layoutManager);

        subCategoryAdapter = new SubCategoryAdapter(getActivity(), subCategoryModelList);
        binding.rvSubCategory.setAdapter(subCategoryAdapter);
    }

    private void getSubCategory(CategoryRequestModel categoryRequestModel){

        //loadingDialog.show();

        RetrofitCallbacks<ResponseModel<ResultModel>> callbacks = new RetrofitCallbacks<ResponseModel<ResultModel>>(getActivity(), loadingDialog) {
            @Override
            public void onSuccess(Response<ResponseModel<ResultModel>> response) {

                ResponseModel responseModel = response.body();

                if(responseModel!=null) {

                    if (responseModel.getStatus() != null && responseModel.getStatus() == 200) {

                        List<SubCategoryModel> list = response.body().getResult().getCategory().get(0).getSubCategories();

                        if(list!=null && list.size()>0){
                            subCategoryModelList.addAll(list);
                            subCategoryAdapter.notifyDataSetChanged();
                        }
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

        ApiClient.getApiInterface().callDashboard(categoryRequestModel).enqueue(callbacks);
    }
}