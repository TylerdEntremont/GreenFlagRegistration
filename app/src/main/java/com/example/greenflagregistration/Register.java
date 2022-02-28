package com.example.greenflagregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText email, PW, RPW;
    TextView emailError, PWError, RPWError;
    Button next;
    ImageButton back;
    ImageView emailClear, PWClear, RPWClear, emailWrong, PWWrong, RPWWrong;
    boolean validEmail, validPW, validRPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.emailEnter);
        PW=findViewById(R.id.PWEnter);
        RPW=findViewById(R.id.RPWEnter);
        emailError=findViewById(R.id.emailError);
        PWError=findViewById(R.id.firstPassError);
        RPWError=findViewById(R.id.RPWError);
        next=findViewById(R.id.registerButton);
        back=findViewById(R.id.backArrow);
        emailClear=findViewById(R.id.emailClear);
        PWClear=findViewById(R.id.PWClear);
        RPWClear=findViewById(R.id.RPWClear);
        emailWrong=findViewById(R.id.emailWrong);
        PWWrong=findViewById(R.id.PWWrong);
        RPWWrong=findViewById(R.id.RPWWrong);
        validEmail=false;
        validPW=false;
        validRPW=false;


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Pattern pattern = Pattern.compile("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})");
                Matcher matcher = pattern.matcher(email.getText().toString());

                if (matcher.matches()){
                    emailError.setVisibility(View.INVISIBLE);
                    emailClear.setVisibility(View.VISIBLE);
                    emailWrong.setVisibility(View.INVISIBLE);
                    validEmail=true;
                }
                else {
                    emailError.setVisibility(View.VISIBLE);
                    emailClear.setVisibility(View.INVISIBLE);
                    emailWrong.setVisibility(View.VISIBLE);
                    validEmail=false;
                }
                next.setEnabled(validEmail && validPW && validRPW);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        PW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pass=PW.getText().toString();
                    if (pass.length()>8 && pass.matches("^.*[^a-zA-Z ].*$") && !pass.toUpperCase().equals(pass) && !pass.toLowerCase().equals(pass)){
                        PWError.setVisibility(View.INVISIBLE);
                        PWClear.setVisibility(View.VISIBLE);
                        PWWrong.setVisibility(View.INVISIBLE);
                        validPW=true;
                    }
                    else{
                        PWError.setVisibility((View.VISIBLE));
                        PWClear.setVisibility(View.INVISIBLE);
                        PWWrong.setVisibility(View.VISIBLE);
                        validPW=false;
                    }
                next.setEnabled(validEmail && validPW && validRPW);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        RPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (RPW.getText().toString().equals(PW.getText().toString())) {
                    RPWError.setVisibility(View.INVISIBLE);
                    RPWClear.setVisibility(View.VISIBLE);
                    RPWWrong.setVisibility(View.INVISIBLE);
                    validRPW=true;
                }
                else{
                    RPWError.setVisibility(View.VISIBLE);
                    RPWClear.setVisibility(View.INVISIBLE);
                    RPWWrong.setVisibility(View.VISIBLE);
                    validRPW=false;
                }
                next.setEnabled(validEmail && validPW && validRPW);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        next.setOnClickListener(view -> {
            SharedPreferences saved = getApplicationContext().getSharedPreferences("GreenFlagRegistrationSP", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = saved.edit();
            if (saved.getString(email.getText().toString(),null)==null) {
                editor.putString(email.getText().toString(),PW.getText().toString());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(getApplicationContext(), "Email Already in Use", Toast.LENGTH_LONG).show();
               }
        });

        back.setOnClickListener(view -> finish());

    }
}