package com.duongnd.quanlythuvien.view.dialog;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.duongnd.quanlythuvien.R;
import com.duongnd.quanlythuvien.model.LoaiSach;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class DialogLoaiSach extends DialogFragment {


    private final int PICK_IMAGE_REQUEST = 71;
    EditText edt_name, edt_nxb;
    Button btn_add_category;
    ImageView img_select;
    String imgURL;
    Uri uri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_loai_sach, container, false);
        edt_name = (EditText) view.findViewById(R.id.edt_name_category);
        edt_nxb = (EditText) view.findViewById(R.id.edt_nxb);
        btn_add_category = (Button) view.findViewById(R.id.btn_add);
        img_select = (ImageView) view.findViewById(R.id.image_select);

        img_select.setOnClickListener(v -> {
            SelectImage();
        });
        btn_add_category.setOnClickListener(v -> {
            saveData();
        });

        return view;
    }

    private void saveData() {
        if (uri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Loại Sách").child(Objects.requireNonNull(uri.getLastPathSegment()));
            storageReference.putFile(uri)
                    .addOnSuccessListener(taskSnapshot -> {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete()) ;
                        Uri urlImage = uriTask.getResult();
                        imgURL = urlImage.toString();
                        uploadData();
                        requireDialog().dismiss();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(requireActivity(), "failed", Toast.LENGTH_SHORT).show();
                    });

        } else {
            Toast.makeText(getContext(), "Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadData() {
        String id = UUID.randomUUID().toString();
        String name = edt_name.getText().toString();
        String nxb = edt_nxb.getText().toString();
        if (name.isEmpty() || nxb.isEmpty()) Toast.makeText(getContext(), "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
        LoaiSach loaiSach = new LoaiSach(id, name, nxb, imgURL);
        FirebaseDatabase.getInstance().getReference("Categories").child(name)
                .setValue(loaiSach).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(requireActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        requireDialog().dismiss();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(requireActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                img_select.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            uri = data.getData();
            img_select.setImageURI(uri);
        }
    }
}