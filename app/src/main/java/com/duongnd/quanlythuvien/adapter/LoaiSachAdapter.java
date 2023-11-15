package com.duongnd.quanlythuvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duongnd.quanlythuvien.R;
import com.duongnd.quanlythuvien.interfaceClick.LoaiSachInterface;
import com.duongnd.quanlythuvien.model.LoaiSach;

import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {

    Context context;

    List<LoaiSach> loaiSachList;
    LoaiSachInterface loaiSachInterface;

    public LoaiSachAdapter(Context context, List<LoaiSach> loaiSachList) {
        this.context = context;
        this.loaiSachList = loaiSachList;
    }

    public void loaiSachInterface(Context context, LoaiSachInterface loaiSachInterface) {
        this.context = context;
        this.loaiSachInterface = loaiSachInterface;
    }

    @NonNull
    @Override
    public LoaiSachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachAdapter.ViewHolder holder, int position) {
        LoaiSach loaiSach = loaiSachList.get(position);
        holder.txt_name.setText(loaiSach.getName());
        holder.layout.setOnClickListener(v -> {
            loaiSachInterface.changeToSach(loaiSach);
        });
    }

    @Override
    public int getItemCount() {
        return loaiSachList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_name;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_loaiSach);
        }
    }
}