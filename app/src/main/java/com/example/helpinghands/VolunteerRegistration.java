package com.example.helpinghands;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VolunteerRegistration extends AppCompatActivity implements View.OnClickListener {
    TextView text_view;
    EditText et_1,et_2,et_3,et_4,et_5;
    Spinner spinner,spinner1;
    Button button;
    FirebaseDatabase database;
    DatabaseReference reference;
    LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_registration);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            //EventLogTags.Description.setVisibility(View.INVISIBLE);
            new AlertDialog.Builder(VolunteerRegistration.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("Please Check your Internetconnection")
                    .setPositiveButton("OK", null).show();
        }else {
            Toast.makeText(this,
                    "Iternet is Available", Toast.LENGTH_SHORT).show();
        }
        et_1=findViewById(R.id.vname);
        et_2=findViewById(R.id.vmobileno);
        et_3=findViewById(R.id.valternate);
        et_4=findViewById(R.id.vemailid);
        et_5=findViewById(R.id.vaddress);
        spinner=findViewById(R.id.sp);
        button=findViewById(R.id.vsubmit);
        button.setOnClickListener(this);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("volunteer");
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vsubmit:
                vsubmit();
                break;
        }

    }

    private void vsubmit() {
        final String name = et_1.getText().toString();
        final String mobile = et_2.getText().toString();
        final String altnumber=et_3.getText().toString();
        final String email = et_4.getText().toString();
        final String address = et_5.getText().toString();
        final String district=spinner.getSelectedItem().toString();
        volunteer v = new volunteer(name, mobile, altnumber,email,address,district);
        reference.child(district).push().setValue(v).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(VolunteerRegistration.this, "Details Save Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("nandu",e.getMessage());

            }
        });
        et_1.setText("");
        et_2.setText("");
        et_3.setText("");
        et_4.setText("");
        et_5.setText("");
        spinner.setSelection(0);
        spinner1.setSelection(0);
    }



    /*@RequiresApi(api = Build.VERSION_CODES.M)
    public void volloc(View view) {
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                text_view.setText("" + latitude + "," + longitude);

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
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,1,listener);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,listener);
    }
*/

}
