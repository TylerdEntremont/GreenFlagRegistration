package com.example.greenflagregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next=findViewById(R.id.nextButton);

        next.setOnClickListener(view -> {
            Intent mIntent = new Intent(getBaseContext(),Register.class);
            startActivity(mIntent);
        });


    }
}