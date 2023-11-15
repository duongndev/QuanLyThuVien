package com.duongnd.quanlythuvien.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.duongnd.quanlythuvien.R;
import com.duongnd.quanlythuvien.view.fragment.NguoiDungFragment;
import com.duongnd.quanlythuvien.view.fragment.PhieuMuonFragment;
import com.duongnd.quanlythuvien.view.fragment.QuanLySachFragment;
import com.duongnd.quanlythuvien.view.fragment.ThanhVienFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if (savedInstanceState == null) {
            replaceFragment(new QuanLySachFragment());
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_sach) {
                replaceFragment(new QuanLySachFragment());
            } else if (item.getItemId() == R.id.nav_thanhVien){
                replaceFragment(new ThanhVienFragment());
            } else if (item.getItemId() == R.id.nav_pm){
                replaceFragment(new PhieuMuonFragment());
            } else if (item.getItemId() == R.id.nav_more){
                replaceFragment(new NguoiDungFragment());
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, fragment);
        transaction.commit();
    }

}