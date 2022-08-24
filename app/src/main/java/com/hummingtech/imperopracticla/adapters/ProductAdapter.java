package com.hummingtech.imperopracticla.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hummingtech.imperopracticla.R;
import com.hummingtech.imperopracticla.databinding.RowProductBinding;
import com.hummingtech.imperopracticla.databinding.RowSubCategoryBinding;
import com.hummingtech.imperopracticla.models.ProductModel;
import com.hummingtech.imperopracticla.models.SubCategoryModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Activity activity;
    List<ProductModel> productModelList;

    public ProductAdapter(Activity activity, List<ProductModel> productModelList) {
        this.activity = activity;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.row_product,parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel productModel = productModelList.get(position);
        holder.bind(productModel);
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowProductBinding binding;

        public ViewHolder(@NonNull RowProductBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(ProductModel productModel){

            binding.tvProductName.setText(productModel.getName());
            Glide.with(activity).load(productModel.getImageName()).into(binding.ivImage);
        }
    }


}
