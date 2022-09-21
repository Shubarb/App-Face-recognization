package com.example.groupnine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class In4Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_in4, container, false);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("User_infor");
        //DatabaseReference ref2 = db.getReference("Attendence");
        //       TextView txtSeen = (TextView) view.findViewById(R.id.txtSeen);

        EditText edtId = (EditText) view.findViewById(R.id.edtId);
        EditText edtName = (EditText) view.findViewById(R.id.edtname);
        EditText edtPosition = (EditText) view.findViewById(R.id.edtposition);
        EditText edtPhone = (EditText) view.findViewById(R.id.edtphone);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyc);
        //RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.recyc2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView2.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
       // recyclerView2.addItemDecoration(dividerItemDecoration);

        List<id1> mListUser = new ArrayList<>();
        UserAdapter userAdapter = new UserAdapter(mListUser);

//        List<id2> mListUser2 = new ArrayList<>();
//        UserAdapter2 userAdapter2 = new UserAdapter2(mListUser2);

        recyclerView.setAdapter(userAdapter);
      //  recyclerView2.setAdapter(userAdapter2);

        Button btnadd = (Button) view.findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Id = Integer.parseInt(edtId.getText().toString().trim());
                String name = edtName.getText().toString().trim();
                String position = edtPosition.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                id1 id1 = new id1(Id, name, position, phone);
                //item is id of member
                String pathObject = String.valueOf(id1.getId());
                ref.child(pathObject).setValue(id1, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference reference) {
                        Toast.makeText(getActivity(), "Push data success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(mListUser != null){
                    mListUser.clear();
                }
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    id1 user = dataSnapshot.getValue(id1.class);
                    mListUser.add(user);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Get list users faild", Toast.LENGTH_SHORT).show();
            }
        });
//        ref2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(mListUser2 != null){
//                    mListUser2.clear();
//                }
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    id2 user2 = dataSnapshot.getValue(id2.class);
//                    mListUser2.add(user2);
//                }
//                userAdapter2.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(), "Get list users faild", Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;

    }

}

