package com.example.groupnine;

import static java.lang.System.out;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class CameraFragment extends Fragment {
    private static final int RESULT_OK = 1;
    int REQUEST_CODE_IMAGE = 1;
    //private ImageView imgIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_camera, container, false);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");
        Button btnCam = (Button)  view.findViewById(R.id.btnCam);
        EditText edt = (EditText) view.findViewById(R.id.edtnamePic);
        ImageView imgIndex = (ImageView) view.findViewById(R.id.imageIndex);
        imgIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
        btnCam.setOnClickListener(new View.OnClickListener() {
            private Object EmailAuthCredential;

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
//"image" + calendar.getTimeInMillis()
                StorageReference mountainsRef = storageRef.child( edt.getText().toString() + ".png");
                imgIndex.setDrawingCacheEnabled(true);
                imgIndex.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imgIndex.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(getActivity(), "Error!!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                       // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(getActivity(), "Success!!!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && requestCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            ImageView imgIndex = (ImageView) getActivity().findViewById(R.id.imageIndex);
            imgIndex.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
