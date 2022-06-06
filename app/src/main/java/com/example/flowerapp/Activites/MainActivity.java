package com.example.flowerapp.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.flowerapp.Fragments.AccountFragment;
import com.example.flowerapp.Fragments.CartFragment;
import com.example.flowerapp.Fragments.HomeFragment;
import com.example.flowerapp.BroadCasts.MyReceiver;
import com.example.flowerapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activateBrodcast();
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.house:
                        openFragment(HomeFragment.newInstance());

                        break;
                    case R.id.cart:
                        openFragment(CartFragment.newInstance("", ""));
                        break;
                    case R.id.user:
                        openFragment(AccountFragment.newInstance("", ""));
                        break;
                }
                return true;
            }
        });
        openFragment(HomeFragment.newInstance());
    }

    private void activateBrodcast() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
            intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
            registerReceiver(myReceiver, intentFilter);
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}