package com.example.helpinghands;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class DistrictwiselistAdapter extends RecyclerView.Adapter<DistrictwiselistAdapter.MyViewHolder> {
    Context ct;
    List<organization> orglist;
    public DistrictwiselistAdapter(Context applicationContext, List<organization> list1) {
        ct=applicationContext;
        orglist=list1;
    }

    @NonNull
    @Override
    public DistrictwiselistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(ct).inflate(R.layout.row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictwiselistAdapter.MyViewHolder holder, int position) {
        holder.tt_tt.append(orglist.get(position).getName()+"\n"+orglist.get(position).getMobileno()+"\n"+orglist.get(position).getEmail()+"\n"+orglist.get(position)
        .getAddress()+"\n"+orglist.get(position).getDistricts()+"\n"+orglist.get(position).getFrom_time()+orglist.get(position)
        .getTo_time());

    }

    @Override
    public int getItemCount() {
        return orglist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tt_tt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tt_tt=itemView.findViewById(R.id.tt);
        }
    }
}
