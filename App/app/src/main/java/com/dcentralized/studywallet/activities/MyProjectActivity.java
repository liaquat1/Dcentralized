package com.dcentralized.studywallet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dcentralized.studywallet.R;

/**
 * Created by Liwei on 1/12/2018.
 */

public class MyProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ((Button)findViewById(R.id.btnApply)).setText("Complete");
        (findViewById(R.id.btnApply)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
