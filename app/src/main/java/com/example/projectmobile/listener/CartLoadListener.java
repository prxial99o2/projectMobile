package com.example.projectmobile.listener;

import com.example.projectmobile.model.CartModel;

import java.util.List;

public interface CartLoadListener {
    void onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
