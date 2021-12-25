package com.example.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.projectmobile.adapter.MyDrinkAdapter;
import com.example.projectmobile.listener.CartLoadListener;
import com.example.projectmobile.listener.DrinkLoadListener;
import com.example.projectmobile.model.CartModel;
import com.example.projectmobile.model.DrinkModel;
import com.example.projectmobile.utils.SpaceItemDecoration;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DrinkLoadListener, CartLoadListener {

    //@BindView(R.id.recycler_drink)
    RecyclerView recycler_drink;
    //@BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    NotificationBadge badge;
    FrameLayout btn_cart;

    DrinkLoadListener drinkLoadListener;
    CartLoadListener cartLoadListener;
    Intent intent= getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_drink = findViewById(R.id.recycler_drink);
        mainLayout = findViewById(R.id.mainLayout);
        badge = findViewById(R.id.badge);
        btn_cart =findViewById(R.id.btncart);

        init();
        loadDrinkFromFirebase();
    }

    private void loadDrinkFromFirebase() {
        List<DrinkModel> drinkModels= new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Drink").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot drinkSnapshot: dataSnapshot.getChildren()){
                        DrinkModel drinkModel = drinkSnapshot.getValue(DrinkModel.class);
                        drinkModel.setKey(drinkSnapshot.getKey());
                        drinkModels.add(drinkModel);
                    }
                    drinkLoadListener.onDrinkLoadSuccess(drinkModels);
                }
                else
                    drinkLoadListener.onDrinkLoadFail("Error");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                drinkLoadListener.onDrinkLoadFail(databaseError.getMessage());

            }
        });
    }

    private void init(){
        ButterKnife.bind(this);
        drinkLoadListener = this;
        cartLoadListener =this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recycler_drink.setLayoutManager(gridLayoutManager);
        recycler_drink.addItemDecoration(new SpaceItemDecoration());

    }

    @Override
    public void onDrinkLoadSuccess(List<DrinkModel> drinkModelList) {
        MyDrinkAdapter adapter = new MyDrinkAdapter(this,drinkModelList);
        recycler_drink.setAdapter(adapter);

    }

    @Override
    public void onDrinkLoadFail(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {

    }

    @Override
    public void onCartLoadFailed(String message) {

    }
}