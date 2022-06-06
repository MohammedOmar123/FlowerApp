package com.example.flowerapp.adapters;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowerapp.Models.CartModel;
import com.example.flowerapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyHolder> {
    Context context;
    ArrayList<CartModel> cartModels;

    public ArrayList<CartModel> getCartModels() {
        return cartModels;
    }

    public void setCartModels(ArrayList<CartModel> cartModels) {
        this.cartModels = cartModels;
        notifyDataSetChanged();
    }

    public CartAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(cartModels.get(position).getImage()).placeholder(R.drawable.sbara).into(holder.im);
        holder.name.setText(cartModels.get(position).getName());
        holder.price.setText(cartModels.get(position).getPrice());
        holder.image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartModels.remove(position);
                notifyDataSetChanged();
            }
        });
          //  @Override
//            public void onClick(View view) {
//                DatabaseReference rf = FirebaseDatabase.getInstance().getReference();
//                Query query = rf.child("flowers");
//                query.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            snapshot.getRef().removeValue();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Log.e(TAG, "onCancelled", error.toException());
//
//                    }
//                });


        //    }
      //  });

    }


    @Override
    public int getItemCount() {
        return cartModels != null ? cartModels.size() : 0;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView im, image_close;
        TextView price, name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.image_purchase);
            price = itemView.findViewById(R.id.name_product);
            name = itemView.findViewById(R.id.price_total_product);
            image_close = itemView.findViewById(R.id.image_close);
        }
    }
}
