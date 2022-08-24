package com.hummingtech.imperopracticla.utils;

import com.hummingtech.imperopracticla.App;

import es.dmoral.toasty.Toasty;

public class ToastUtils {

    private static final String INOTA = "No Internet Connection";

    public static void showErrorToast(String message){
        Toasty.error(App.getInstance(),message,Toasty.LENGTH_SHORT,false).show();
    }

    public static void showSuccessToast(String message){
        Toasty.success(App.getInstance(),message,Toasty.LENGTH_SHORT,false).show();
    }


    public static void showNetworkErrorToast() {
        Toasty.error(App.getInstance(),INOTA,Toasty.LENGTH_SHORT,false).show();
    }
}