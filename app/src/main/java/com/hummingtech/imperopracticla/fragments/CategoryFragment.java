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
import com.hummingtech.imperopracticla.adapters.ProductAdapter;
import com.hummingtech.imperopracticla.adapters.SubCategoryAdapter;
import com.hummingtech.imperopracticla.databinding.FragmentCategoryBinding;
import com.hummingtech.imperopracticla.dialog.LoadingDialog;
import com.hummingtech.imperopracticla.models.CategoryModel;
import com.hummingtech.imperopracticla.models.CategoryRequestModel;
import com.hummingtech.imperopracticla.models.ProductModel;
import com.hummingtech.imperopracticla.models.ProductRequestModel;
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
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class CategoryFragment extends Fragment {

    FragmentCategoryBinding binding;
    CategoryModel categoryModel;
    LoadingDialog loadingDialog;

    CategoryRequestModel categoryRequestModel;
    List<SubCategoryModel> subCategoryModelList = new ArrayList<>();

    SubCategoryAdapter subCategoryAdapter;

    NoPaginate noPaginate;

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
        setPagination();
        getSubCategory(categoryRequestModel);
    }

    private void init(){

        categoryRequestModel = new CategoryRequestModel(categoryModel.getId(),1);

        loadingDialog = new LoadingDialog(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvSubCategory.setLayoutManager(layoutManager);

        subCategoryAdapter = new SubCategoryAdapter(getActivity(), subCategoryModelList, new SubCategoryAdapter.OnItemListener() {
            @Override
            public void loadMoreProduct(int position, SubCategoryModel subCategoryModel, NoPaginate noPaginate, ProductAdapter productAdapter) {
                getProductList(position, subCategoryModel, noPaginate, productAdapter);
            }
        });
        binding.rvSubCategory.setAdapter(subCategoryAdapter);


    }

    private void setPagination(){

        noPaginate = NoPaginate.with(binding.rvSubCategory)
                .setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        //http or db request

                        categoryRequestModel.setPageIndex(categoryRequestModel.getPageIndex()+1);
                        getSubCategory(categoryRequestModel);
                    }
                })
                .build();
    }

    private void getSubCategory(CategoryRequestModel categoryRequestModel){

        //loadingDialog.show();
        noPaginate.showLoading(true);

        RetrofitCallbacks<ResponseModel<ResultModel>> callbacks = new RetrofitCallbacks<ResponseModel<ResultModel>>(getActivity(), loadingDialog) {
            @Override
            public void onSuccess(Response<ResponseModel<ResultModel>> response) {

                noPaginate.showLoading(false);

                ResponseModel responseModel = response.body();

                if(responseModel!=null) {

                    if (responseModel.getStatus() != null && responseModel.getStatus() == 200) {

                        List<SubCategoryModel> list = response.body().getResult().getCategory().get(0).getSubCategories();

                        if(list!=null && list.size()>0){
                            subCategoryModelList.addAll(list);
                            subCategoryAdapter.notifyDataSetChanged();
                        }
                        else {
                            noPaginate.setNoMoreItems(true);
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

    private void getProductList(int position, SubCategoryModel subCategoryModel, NoPaginate paginate, ProductAdapter productAdapter){

        subCategoryModel.setPageIndex(subCategoryModel.getPageIndex()+1);

        //loadingDialog.show();
        paginate.showLoading(true);

        RetrofitCallbacks<ResponseModel<List<ProductModel>>> callbacks = new RetrofitCallbacks<ResponseModel<List<ProductModel>>>(getActivity(),loadingDialog) {
            @Override
            public void onSuccess(Response<ResponseModel<List<ProductModel>>> response) {

                paginate.showLoading(false);

                List<ProductModel> list = response.body().getResult();


                if(list!=null && list.size()>0){

                    subCategoryModel.getProduct().addAll(list);
                    productAdapter.notifyItemChanged(position);
                }
                else {
                    paginate.setNoMoreItems(true);
                }
            }

            @Override
            public void onFail(@NonNull Call<ResponseModel<List<ProductModel>>> call, @NonNull Throwable t) {

                paginate.showLoading(false);
            }
        };

        ProductRequestModel productRequestModel = new ProductRequestModel(subCategoryModel.getId(), subCategoryModel.getPageIndex());

        ApiClient.getApiInterface().callProductList(productRequestModel).enqueue(callbacks);
    }

}