package com.example.helpinghands;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDonor extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3,e4;
    Button b1;
    Spinner sp1,sp2;
    CheckBox c1,c2,c3,c4,c5;
    LocationManager manager;
    FirebaseDatabase database;
    DatabaseReference reference,reference1;
    private String selectedDistrict;
    List<organization> phonenumberList;
    String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_donor);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            //EventLogTags.Description.setVisibility(View.INVISIBLE);
            new AlertDialog.Builder(RegistrationDonor.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("Please Check your Internetconnection")
                    .setPositiveButton("OK", null).show();
        }else {
            Toast.makeText(this,
                    "Iternet is Available", Toast.LENGTH_SHORT).show();
        }

        e1=findViewById(R.id.name);
        e2= findViewById(R.id.mobile);
        e3= findViewById(R.id.email);
        e4=findViewById(R.id.daddress);
        b1=findViewById(R.id.submit);
        sp1=findViewById(R.id.sp);
        sp2= findViewById(R.id.org);
        c1= findViewById(R.id.food);
        c2= findViewById(R.id.clothes);
        c3= findViewById(R.id.books);
        c4= findViewById(R.id.money);
        c5= findViewById(R.id.others);
        b1.setOnClickListener(this);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("donor");
        reference1 = database.getReference("organization");
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
       /* sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                submit();
                break;
        }
    }

    private void submit() {

        final String name = e1.getText().toString();
        final String mobile = e2.getText().toString();
        final String email = e3.getText().toString();
        final String address = e4.getText().toString();
        final String district=sp1.getSelectedItem().toString();
        final String organizations=sp2.getSelectedItem().toString();
        StringBuilder builder = new StringBuilder();

        //phonenumber = phonenumberList.get(sp1.getSelectedItemPosition()).getMobileno();
        if(c1.isChecked()){
            builder.append(c1.getText().toString()+",");
        }
        if(c2.isChecked()){
            builder.append(c2.getText().toString()+",");
        }
        if (c3.isChecked()){
            builder.append(c3.getText().toString()+",");
        }
        if (c4.isChecked()){
            builder.append(c4.getText().toString()+",");
        }
        if (c5.isChecked()){
            builder.append(c5.getText().toString()+",");
        }
        String typeOfDonation = builder.toString();
        donor u = new donor(name, mobile, email,address,district,organizations,typeOfDonation);
        reference .child(district).push().setValue(u).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(RegistrationDonor.this, "Details Save Successfully", Toast.LENGTH_SHORT).show();
                String message = "Dear Sir/Madam \n  Food is Available at "+address+"\n"+name+"\n"+mobile;
                sendSMS(phonenumberList.get(sp2.getSelectedItemPosition()).getMobileno(),message);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("nandu",e.getMessage());

            }


        });

        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        sp1.setSelection(0);
        sp2.setSelection(0);
        if (c1.isChecked())
            c1.setChecked(false);
            c2.setChecked(false);
            c3.setChecked(false);
            c4.setChecked(false);
            c5.setChecked(false);



    }



/*
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void donloc(View view) {

        LocationListener listener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                t2.setText("" + latitude + "," + longitude);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, listener);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
    }*/


    public void getOrganizations(View view) {
        String district = sp1.getSelectedItem().toString();

        final List<String> organizationsList = new ArrayList<>();
        phonenumberList = new ArrayList<organization>();
        reference1.child(district).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    organization org  = snapshot.getValue(organization.class);
                    organizationsList.add(org.getName());
                    phonenumberList.add(org);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrationDonor.this,android.R.layout.simple_list_item_1,organizationsList);
                sp2.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}