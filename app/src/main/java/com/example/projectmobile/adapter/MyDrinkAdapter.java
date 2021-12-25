package com.example.projectmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmobile.R;
import com.example.projectmobile.model.DrinkModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyDrinkAdapter extends RecyclerView.Adapter<MyDrinkAdapter.MyDrinkViewHolder> {

    private Context context;
    private List<DrinkModel> drinkModelList;

    public MyDrinkAdapter(Context context, List<DrinkModel> drinkModelList) {
        this.context=context;
        this.drinkModelList=drinkModelList;
    }

    @NonNull
    @Override
    public MyDrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyDrinkViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_drinkitem,parent,false));

    }
    @Override
    public void onBindViewHolder(@NonNull MyDrinkViewHolder holder, int position) {
        Glide.with(context).load(drinkModelList.get(position).getImage()).into(holder.iv_item);
        holder.tv_price.setText(new StringBuilder("RM").append(drinkModelList.get(position).getPrice()));
        holder.tv_name.setText(new StringBuilder().append(drinkModelList.get(position).getName()));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyDrinkViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_item)
        ImageView iv_item;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_price)
        TextView tv_price;

        private Unbinder unbinder;

        public MyDrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder= ButterKnife.bind(this,itemView);
        }
    }
}
