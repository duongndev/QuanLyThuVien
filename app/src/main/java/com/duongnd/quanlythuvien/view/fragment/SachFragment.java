package com.duongnd.quanlythuvien.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duongnd.quanlythuvien.R;
import com.duongnd.quanlythuvien.model.LoaiSach;


public class SachFragment extends Fragment {

    public static final String TAG = SachFragment.class.getName();
    TextView textView;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        textView = (TextView) view.findViewById(R.id.idLoai);


        Bundle bundle = getArguments();
        if (bundle != null) {
            LoaiSach loaiSach = (LoaiSach) bundle.get("LoaiSach");
            if (loaiSach != null) {
                textView.setText(loaiSach.getId());
//                id = loaiSach.getId();
                Log.d("Loai Sach", loaiSach.getId());
            }
        }
        return view;
    }

}