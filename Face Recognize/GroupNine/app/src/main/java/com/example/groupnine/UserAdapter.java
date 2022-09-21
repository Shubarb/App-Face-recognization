package com.example.groupnine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<id1> mListUser;

    public  UserAdapter(List<id1>mListUser){
        this.mListUser = mListUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        id1 user = mListUser.get(position);
        if (user == null){
            return;
        }
        holder.txtID.setText("ID: " + user.getId());
        holder.txtName.setText("Name: "+ user.getName());
        holder.txtPosition.setText("Position: "+ user.getPosition());
        holder.txtPhone.setText("Phone: "+ user.getPhone());
    }

    @Override
    public int getItemCount() {
        if(mListUser != null){
            return mListUser.size();
        }
        return 0;
    }

    public  class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView txtID;
        private TextView txtName;
        private TextView txtPosition;
        private TextView txtPhone;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtName = itemView.findViewById(R.id.txtName);
            txtPosition = itemView.findViewById(R.id.txtPosition);
            txtPhone = itemView.findViewById(R.id.txtPhone);
        }
    }

}
