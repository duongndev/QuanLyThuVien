package com.duongnd.quanlythuvien.view.fragment;

import static com.duongnd.quanlythuvien.view.fragment.SachFragment.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duongnd.quanlythuvien.R;
import com.duongnd.quanlythuvien.adapter.LoaiSachAdapter;
import com.duongnd.quanlythuvien.interfaceClick.LoaiSachInterface;
import com.duongnd.quanlythuvien.model.LoaiSach;
import com.duongnd.quanlythuvien.view.dialog.DialogLoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class QuanLySachFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    LoaiSachAdapter adapter;
    List<LoaiSach> loaiSachList;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        loaiSachList = new ArrayList<>();
        adapter = new LoaiSachAdapter(getContext(), loaiSachList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Categories");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loaiSachList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    LoaiSach loaiSach = dataSnapshot.getValue(LoaiSach.class);
                    Log.d(TAG, "onDataChange: " + loaiSach.getName());
                    loaiSachList.add(loaiSach);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: " + error.getMessage());
            }
        });


        floatingActionButton.setOnClickListener(v -> {
            DialogLoaiSach dialogLoaiSach = new DialogLoaiSach();
            dialogLoaiSach.show(getParentFragmentManager(), dialogLoaiSach.getTag());
        });

        adapter.loaiSachInterface(getContext(), new LoaiSachInterface() {
            @Override
            public void changeToSach(LoaiSach loaiSach) {
                Toast.makeText(getContext(), loaiSach.getName(), Toast.LENGTH_SHORT).show();
                changeSach(loaiSach);
            }
        });
    }


    private void changeSach(LoaiSach loaiSach) {
        SachFragment sachFragment = new SachFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("LoaiSach", loaiSach);
        sachFragment.setArguments(bundle);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_view, sachFragment);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}