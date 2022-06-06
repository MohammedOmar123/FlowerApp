package com.example.flowerapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.flowerapp.Activites.DetailsActivity;
import com.example.flowerapp.adapters.FlowersAdapter;
import com.example.flowerapp.Models.Flower;
import com.example.flowerapp.Models.Plant;
import com.example.flowerapp.R;
import com.example.flowerapp.TypeProjectInterface;
import com.example.flowerapp.adapters.PlantAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    DatabaseReference databaseReference;
    ArrayList<Flower> flowerArrayList;
    ArrayList<Plant> plant;
    FlowersAdapter adapter;
    PlantAdapter adapter1;

    public static final String Item_KEY = "churches_key";

    public HomeFragment() {
    }
    // 1- مراجعة هدا الكود المتعلق بفتح الفراقمنت و يفضل أن يصبح على الطريقة التي تعلمناها
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        flowerArrayList = new ArrayList<>();
        RecyclerView recyclerview = view.findViewById(R.id.recycleview_home);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerview.setLayoutManager(layoutManager);
        databaseReference = FirebaseDatabase.getInstance().getReference("flowers");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Flower ff = dataSnapshot.getValue(Flower.class);
// مراجعة هنا ضروروي
                    flowerArrayList.add(ff);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"No Data", Toast.LENGTH_LONG).show();
                Log.e("error","error");
            }
        });// pass arrayList to adapter
        adapter = new FlowersAdapter(getActivity(), flowerArrayList, new TypeProjectInterface() {
            @Override
            public void onItemClick(int Id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Item_KEY, Id);
                startActivity(intent);

            }
        });
        recyclerview.setAdapter(adapter);

        plant = new ArrayList<>();
        RecyclerView recyclerview2 = view.findViewById(R.id.recycleview_plant);
        LinearLayoutManager  layoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerview2.setLayoutManager(layoutManager1);
        databaseReference = FirebaseDatabase.getInstance().getReference("plant");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    Plant pp = dataSnapshot.getValue(Plant.class);
                    plant.add(pp);
                }
                adapter1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"nodata", Toast.LENGTH_LONG).show();
                Log.e("error","error");
            }
        });
        adapter1 = new PlantAdapter(getActivity(), plant, new TypeProjectInterface() {
            @Override
            public void onItemClick(int Id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra(Item_KEY, Id);
                startActivity(intent);
            }
        });
        recyclerview2.setAdapter(adapter1);
        return view ;
    }

}