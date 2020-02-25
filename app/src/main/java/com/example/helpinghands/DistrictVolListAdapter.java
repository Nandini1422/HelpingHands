package com.example.helpinghands;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class DistrictVolListAdapter extends RecyclerView.Adapter<DistrictVolListAdapter.MyVolViewHolder> {
    Context context;
    List<volunteer> vollist;
    public DistrictVolListAdapter(Context applicationContext, List<volunteer> list2) {
        context=applicationContext;
        vollist=list2;
    }

    @NonNull
    @Override
    public DistrictVolListAdapter.MyVolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.vol,parent,false);
        return new MyVolViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictVolListAdapter.MyVolViewHolder holder, int position) {
        holder.ttt_ttt.append(vollist.get(position).getName()+"\n"+vollist.get(position).getEmail()+"\n"+vollist.get(position).getMobileno()+"\n"+vollist.get(position)
                .getAddress()+"\n"+vollist.get(position).getDistricts()+"\n"+vollist.get(position).getAltnumber());

    }

    @Override
    public int getItemCount() {
        return vollist.size();
    }

    public class MyVolViewHolder extends RecyclerView.ViewHolder {
        TextView ttt_ttt;
        public MyVolViewHolder(@NonNull View itemView) {
            super(itemView);
            ttt_ttt=itemView.findViewById(R.id.ttt);
        }
    }
}
