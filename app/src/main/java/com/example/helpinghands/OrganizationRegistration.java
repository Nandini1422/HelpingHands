package com.example.helpinghands;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class OrganizationRegistration extends AppCompatActivity implements View.OnClickListener {
    TextView tv1,tv_fromTime,tv_totime;
    EditText editText1,editText2,editText3,editText4;
    Button button1;
    Spinner dsp,dsp1;
    FirebaseDatabase database;
    DatabaseReference reference;
    int c_hour,c_minuite;
    LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_registration);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            //EventLogTags.Description.setVisibility(View.INVISIBLE);
            new AlertDialog.Builder(OrganizationRegistration.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("Please Check your Internetconnection")
                    .setPositiveButton("OK", null).show();
        }else {
            Toast.makeText(this,
                    "Iternet is Available", Toast.LENGTH_SHORT).show();
        }
        tv1=findViewById(R.id.textview);
        tv_fromTime = findViewById(R.id.fromtimetextview);
        tv_totime=findViewById(R.id.totextview);
        editText1=findViewById(R.id.oname);
        editText2=findViewById(R.id.omobileno);
        editText3=findViewById(R.id.oemailid);
        editText4=findViewById(R.id.orgaddress);
        dsp=findViewById(R.id.spd);
        button1 = findViewById(R.id.osubmit);
        button1.setOnClickListener(this);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("organization");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.osubmit:
                osubmit();
                break;
        }

    }

    private void osubmit() {
        final String name = editText1.getText().toString();
        final String mobile = editText2.getText().toString();
        final String email = editText3.getText().toString();
        final String address = editText4.getText().toString();
        final String district=dsp.getSelectedItem().toString();
        final String from_time=tv_fromTime.getText().toString();
        final String to_time=tv_totime.getText().toString();
        organization o = new organization(name, mobile, email,address,district,from_time,to_time);
        reference .child(district).push().setValue(o).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(OrganizationRegistration.this, "Details Save Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("nandu",e.getMessage());

            }
        });
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        dsp.setSelection(0);
        dsp1.setSelection(0);
        tv_fromTime.setText("");
        tv_totime.setText("");
    }

    public void openTimePicker(View view) {
        Calendar c = Calendar.getInstance();
        c_hour = c.get(Calendar.HOUR_OF_DAY);
        c_minuite = c.get(Calendar.MINUTE);
        TimePickerDialog tdailog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String myTime =i+":"+i1;
                tv_fromTime.setText(myTime);
            }
        },c_hour,c_minuite,false);
        tdailog.show();
    }
    public void timepicker(View view) {
        Calendar c = Calendar.getInstance();
        c_hour = c.get(Calendar.HOUR_OF_DAY);
        c_minuite = c.get(Calendar.MINUTE);
        TimePickerDialog tdailog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String myTime =i+":"+i1;
                tv_totime.setText(myTime);
            }
        },c_hour,c_minuite,false);
        tdailog.show();
    }

    /*@RequiresApi(api = Build.VERSION_CODES.M)
    public void orgloc(View view) {
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tv1.setText(""+latitude+","+longitude);

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
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,1,listener);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,listener);*/
    }




