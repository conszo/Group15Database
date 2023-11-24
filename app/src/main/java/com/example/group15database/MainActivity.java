package com.example.group15database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper db;
    EditText txt_email, txt_password;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseHelper(this);

        txt_email = findViewById(R.id.email_txt);
        txt_password = findViewById(R.id.password_txt);

        btnRegister = findViewById(R.id.register_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = txt_email.getText().toString();
                String Password = txt_password.getText().toString();

                if (Email.equals("") || Password.equals("")) { // Fixed the condition for checking empty fields
                    Toast.makeText(getApplicationContext(), "Fields are empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkEmail = db.checkEmail(Email);
                    if (checkEmail) { // Simplified the condition
                        Boolean insert = db.insert(Email, Password);
                        if (insert) { // Simplified the condition
                            Toast.makeText(getApplicationContext(), "Register Successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email already Exists",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
