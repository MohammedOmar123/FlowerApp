package com.example.flowerapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowerapp.Activites.DetailsActivity;
import com.example.flowerapp.Models.Plant;
import com.example.flowerapp.R;
import com.example.flowerapp.TypeProjectInterface;

import java.util.ArrayList;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyHolder> {
    Context context;
    ArrayList<Plant> plants ;
    TypeProjectInterface typeprojectinterface;

    public PlantAdapter(Context context , ArrayList<Plant> plants , TypeProjectInterface typeprojectinterface) {
       this.plants = plants;
        this.context = context;
        this.typeprojectinterface = typeprojectinterface;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(plants.get(position).getImage()).placeholder(R.drawable.ic_launcher_foreground).into(holder.im);
        holder.textView.setText(plants.get(position).getName());
        holder.price.setText(plants.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("image",plants.get(position).getImage());
                intent.putExtra("name", plants.get(position).getName());
                intent.putExtra("price", plants.get(position).getPrice());
                intent.putExtra("details", plants.get(position).getDetails());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
        ImageView im;
        TextView textView,price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.imagePlant);
            textView = itemView.findViewById(R.id.textPlant);
            price = itemView.findViewById(R.id.price);
        }
    }

}
