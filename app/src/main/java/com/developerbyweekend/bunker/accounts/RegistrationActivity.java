package com.developerbyweekend.bunker.accounts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.main.MainActivity;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView txtRegistrationBtn = (TextView) findViewById(R.id.txtRegistrationBtn);
        txtRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);

            }
        });

    }
}
