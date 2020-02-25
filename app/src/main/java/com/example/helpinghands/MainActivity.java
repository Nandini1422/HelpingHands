package com.example.helpinghands;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View view;
    TextView tv1;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.textview);
        view=this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.white);

    }

        public void donate(View view) {
            String userdata = tv1.getText().toString();
            Intent i = new Intent(this,RegistrationDonor.class);
            i.putExtra("Key",userdata);
            startActivity(i);

    }

    public void vreg(View view) {
        String userdata = tv1.getText().toString();
        Intent i = new Intent(this,VolunteerRegistration.class);
        i.putExtra("Key",userdata);
        startActivity(i);

    }

    public void oreg(View view) {
        String userdata = tv1.getText().toString();
        Intent i = new Intent(this,OrganizationRegistration.class);
        i.putExtra("Key",userdata);
        startActivity(i);
    }

    public void distlist(View view) {
        String userdata = tv1.getText().toString();
        Intent i = new Intent(this,Districtwiselist.class);
        i.putExtra("Key",userdata);
        startActivity(i);
    }
}
