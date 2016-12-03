package com.developerbyweekend.bunker.accounts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.api.Callable;
import com.developerbyweekend.bunker.attendance.AttendanceActivity;
import com.developerbyweekend.bunker.models.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txtLogin= (TextView) findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((TextView)findViewById(R.id.editUsername)).getText().toString();
                String password = ((TextView)findViewById(R.id.editPassword)).getText().toString();
                User.login(getApplicationContext(), username, password, new Callable() {
                    @Override
                    public void onResponse(Object data) {
                        ((User)data).saveLocal(LoginActivity.this);
                        Intent myIntent = new Intent(LoginActivity.this, AttendanceActivity.class);
                        LoginActivity.this.startActivity(myIntent);
                    }

                    @Override
                    public void onError(Exception error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

}
