package com.example.bastien.gsb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoPatient extends AppCompatActivity {

    private TextView tvInfoPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_patient);

        Bundle b = getIntent().getExtras();
        tvInfoPatient = (TextView) findViewById(R.id.tvInfoPatient);
        String val = b.getString("message");
        tvInfoPatient.setText(val);
    }
}
