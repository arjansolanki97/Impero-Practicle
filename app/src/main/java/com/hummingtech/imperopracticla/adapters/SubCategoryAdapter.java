package com.hummingtech.imperopracticla.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hummingtech.imperopracticla.R;
import com.hummingtech.imperopracticla.databinding.RowSubCategoryBinding;
import com.hummingtech.imperopracticla.dialog.LoadingDialog;
import com.hummingtech.imperopracticla.models.ProductRequestModel;
import com.hummingtech.imperopracticla.models.SubCategoryModel;

import java.util.List;

import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    Activity activity;
    List<SubCategoryModel> subCategoryModelList;
    LoadingDialog loadingDialog;

    OnItemListener itemListener;

    public interface OnItemListener{
        void loadMoreProduct(int position, SubCategoryModel subCategoryModel, NoPaginate noPaginate, ProductAdapter productAdapter);
    }

    public SubCategoryAdapter(Activity activity, List<SubCategoryModel> subCategoryModelList, OnItemListener itemListener) {
        this.activity = activity;
        this.subCategoryModelList = subCategoryModelList;
        this.itemListener=itemListener;
        loadingDialog = new LoadingDialog(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowSubCategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.row_sub_category,parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubCategoryModel subCategoryModel = subCategoryModelList.get(position);
        holder.bind(position,subCategoryModel);
    }

    @Override
    public int getItemCount() {
        return subCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowSubCategoryBinding binding;
        NoPaginate noPaginate = null;
        ProductAdapter productAdapter;

        public ViewHolder(@NonNull RowSubCategoryBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(int position, SubCategoryModel subCategoryModel){

            binding.tvSubCategoryTitle.setText(subCategoryModel.getName());

            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
            binding.rvSubCategory.setLayoutManager(layoutManager);

            productAdapter = new ProductAdapter(activity, subCategoryModel.getProduct());
            binding.rvSubCategory.setAdapter(productAdapter);

            setPagination(position, subCategoryModel);
        }

        private void setPagination(int position, SubCategoryModel subCategoryModel){

            noPaginate = NoPaginate.with(binding.rvSubCategory)
                    .setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore() {

                            itemListener.loadMoreProduct(position, subCategoryModel, noPaginate, productAdapter);

                        }
                    })
                    .build();
        }

    }
}
