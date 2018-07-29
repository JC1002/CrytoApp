package com.example.jn1002.crytoapp.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.jn1002.crytoapp.R;
import com.example.jn1002.crytoapp.helper.InputValidation;
import com.example.jn1002.crytoapp.sql.DatabaseHelper;

public class ForgotPassword extends AppCompatActivity {

    private TextInputEditText textInputEditTextEmail;
    private TextInputLayout textInputLayoutEmail;

    private AppCompatButton appCompatButtonConfirm;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private NestedScrollView nestedScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        appCompatButtonConfirm = (AppCompatButton) findViewById(R.id.appCompatButtonConfirm);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        databaseHelper = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

        setTitle("Recover password");
        appCompatButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });

    }

    private void verifyFromSQLite(){

        if (textInputEditTextEmail.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill your email", Toast.LENGTH_SHORT).show();
            return;
        }


        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            Intent accountsIntent = new Intent(this, ConfirmPassword.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText("");
    }
}
