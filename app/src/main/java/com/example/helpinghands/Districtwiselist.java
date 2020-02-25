package com.example.helpinghands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Districtwiselist extends AppCompatActivity {
    Spinner sp_dist;
    RecyclerView rv;
    FirebaseDatabase database,database2;
    DatabaseReference reference,reference2;
    RadioButton vregister,oregister;

    List<organization> list1;
    List<volunteer>  list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_districtwiselist);
        sp_dist=findViewById(R.id.disspi);
        rv = findViewById(R.id.recycler);
        vregister=findViewById(R.id.dvol);
        oregister=findViewById(R.id.dorg);

        list1=new ArrayList<>();
        list2=new ArrayList<>();

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("organization");
        database2=FirebaseDatabase.getInstance();
        reference2=database2.getReference("volunteer");
    }

    public void search(View view) {

      final String sppds=sp_dist.getSelectedItem().toString();
        if(oregister.isChecked()){
            reference.child(sppds).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        organization o=snapshot.getValue(organization.class);
                        list1.add(o);
                    }
                    rv.setLayoutManager(new LinearLayoutManager(Districtwiselist.this));
                    DistrictwiselistAdapter adapter=new DistrictwiselistAdapter(getApplicationContext(),list1);
                    rv.setAdapter(adapter);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        if(vregister.isChecked()){
            reference2.child(sppds).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        volunteer v=snapshot.getValue(volunteer.class);
                        list2.add(v);
                    }
                    rv.setLayoutManager(new LinearLayoutManager(Districtwiselist.this));
                    DistrictVolListAdapter adapt=new DistrictVolListAdapter(getApplicationContext(),list2);
                    rv.setAdapter(adapt);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }
}
