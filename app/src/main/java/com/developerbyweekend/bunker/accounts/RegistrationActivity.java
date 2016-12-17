package com.developerbyweekend.bunker.accounts;

import android.content.Intent;
import android.provider.Settings.Secure;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.api.Callable;
import com.developerbyweekend.bunker.attendance.AttendanceActivity;
import com.developerbyweekend.bunker.models.User;


public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView txtRegistrationBtn = (TextView) findViewById(R.id.txtRegistrationBtn);
        txtRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((TextView)findViewById(R.id.editUsername)).getText().toString();
                String password = ((TextView)findViewById(R.id.editPassword)).getText().toString();
                String email = ((TextView)findViewById(R.id.editContact)).getText().toString();
                String device_id = Secure.getString(RegistrationActivity.this.getContentResolver(), Secure.ANDROID_ID);

                new User(username,device_id,null,email,password).register(getApplicationContext(), new Callable() {
                    @Override
                    public void onResponse(Object data) {
                        User user = (User)data;
                        user.saveLocal(RegistrationActivity.this);
                        Intent myIntent = new Intent(RegistrationActivity.this, AttendanceActivity.class);
                        RegistrationActivity.this.startActivity(myIntent);
                    }

                    @Override
                    public void onError(Exception error) {
                        Toast.makeText(getApplicationContext(),"Error "+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }


}
