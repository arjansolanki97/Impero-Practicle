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
import com.hummingtech.imperopracticla.models.CategoryModel;
import com.hummingtech.imperopracticla.models.SubCategoryModel;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    Activity activity;
    List<SubCategoryModel> subCategoryModelList;

    public SubCategoryAdapter(Activity activity, List<SubCategoryModel> subCategoryModelList) {
        this.activity = activity;
        this.subCategoryModelList = subCategoryModelList;
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
        holder.bind(subCategoryModel);
    }

    @Override
    public int getItemCount() {
        return subCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowSubCategoryBinding binding;

        public ViewHolder(@NonNull RowSubCategoryBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(SubCategoryModel subCategoryModel){

            binding.tvSubCategoryTitle.setText(subCategoryModel.getName());

            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
            binding.rvSubCategory.setLayoutManager(layoutManager);

            ProductAdapter productAdapter = new ProductAdapter(activity, subCategoryModel.getProduct());
            binding.rvSubCategory.setAdapter(productAdapter);
        }
    }
}
