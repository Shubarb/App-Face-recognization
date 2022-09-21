package com.example.groupnine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.UserViewHolder> {

    private List<id2> mListUser2;

    public  UserAdapter2(List<id2>mListUser2){
        this.mListUser2 = mListUser2;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user2,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        id2 user = mListUser2.get(position);
        if (user == null){
            return;
        }
       // holder.txtID.setText("ID: " + user.getId());
        holder.txtName.setText("Name: "+ user.getName());
        holder.txtTime.setText("time: "+ user.getTime());
        //holder.txtAttendance.setText("Attendance: "+ user.getAttendance());
    }

    @Override
    public int getItemCount() {
        if(mListUser2 != null){
            return mListUser2.size();
        }
        return 0;
    }

    public  class UserViewHolder extends RecyclerView.ViewHolder{

      //  private TextView txtID;
        private TextView txtName;
        private TextView txtTime;
     //   private TextView txtAttendance;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
          //  txtID = itemView.findViewById(R.id.txtID);
            txtName = itemView.findViewById(R.id.txtName);
            txtTime = itemView.findViewById(R.id.txtTime);
          //  txtAttendance = itemView.findViewById(R.id.txtAttendance);
        }
    }

}
