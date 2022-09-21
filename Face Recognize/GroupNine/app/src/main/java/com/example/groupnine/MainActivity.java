package com.example.groupnine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar tbar;
    private NavigationView nvDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbar = findViewById(R.id.toolbar);
        mDrawer = findViewById(R.id.drawerlayout);
        nvDrawer = findViewById(R.id.nvView);

        setSupportActionBar(tbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        setupDrawerContent(nvDrawer);
        loadFragment(new HomeFragment());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return false;
            }
        });
    }
    public void selectDrawerItem(MenuItem menuItem){
        Fragment fragment;
        switch (menuItem.getItemId()){
            case R.id.home:
                fragment = new HomeFragment();
                loadFragment(fragment);
                break;
            case R.id.cam:
                fragment = new CameraFragment();
                loadFragment(fragment);
                break;
            case R.id.qr:
                fragment = new QRFragment();
                loadFragment(fragment);
                break;
            case R.id.temp:
                fragment = new TempFragment();
                loadFragment(fragment);
                break;
            case R.id.in4:
                fragment = new In4Fragment();
                loadFragment(fragment);
                break;
            default:
                fragment = new HomeFragment();
                loadFragment(fragment);
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager() ;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flConnect,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}