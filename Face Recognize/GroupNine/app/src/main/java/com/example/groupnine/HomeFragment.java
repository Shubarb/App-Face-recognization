package com.example.groupnine;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {
    private BottomNavigationView navigationView;
    FragmentActivity fragmentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        navigationView = view.findViewById(R.id.nvbotView);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        fragmentActivity = (FragmentActivity)context;
        super.onAttach(context);
    }
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.homeNav:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.camNav:
                    fragment = new CameraFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.qrNav:
                    fragment = new QRFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.tempNav:
                    fragment = new TempFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.in4Nav:
                    fragment = new In4Fragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager() ;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContainer,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}