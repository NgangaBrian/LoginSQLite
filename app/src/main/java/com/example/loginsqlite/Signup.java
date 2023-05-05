package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText email, password, confirmPword;
    Button register;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signupPword);
        confirmPword = findViewById(R.id.pwordConfirm);
        register = findViewById(R.id.registerBtn);
        textView = findViewById(R.id.textView2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String ConfirmPword = confirmPword.getText().toString();

                if(Email.equals("") || Password.equals("") || ConfirmPword.equals(""))
                    Toast.makeText(Signup.this, "Fields cannot be empty!!", Toast.LENGTH_SHORT).show();
                else {
                    if (Password.equals(ConfirmPword)){
                        Boolean checkEmail = databaseHelper.checkEmail(Email);

                        if(checkEmail == false ){
                            Boolean insert = databaseHelper.insertData(Email, Password);

                            if(insert == true){
                                Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this, Login.class);
                                startActivity(intent);
                                email.setText("");
                                password.setText("");
                                confirmPword.setText("");
                            }
                            else {
                                Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Signup.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Signup.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }
}