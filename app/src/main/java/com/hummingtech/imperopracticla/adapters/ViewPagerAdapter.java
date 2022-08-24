package com.hummingtech.imperopracticla.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hummingtech.imperopracticla.fragments.CategoryFragment;
import com.hummingtech.imperopracticla.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<CategoryModel> categoryModelList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<CategoryModel> categoryModelList) {
        super(fm);
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new CategoryFragment(categoryModelList.get(position));
    }

    @Override
    public int getCount() {
        return categoryModelList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoryModelList.get(position).getName();
    }
}
