package com.example.projectmobile.listener;

import com.example.projectmobile.model.DrinkModel;

import java.util.List;

public interface DrinkLoadListener {
    void onDrinkLoadSuccess(List<DrinkModel> drinkModelList);
    void onDrinkLoadFail(String message);
}
