package com.swe401.getasset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Username = (EditText) findViewById(R.id.login_username);
        Password = (EditText) findViewById(R.id.login_password);
        Info = (TextView) findViewById(R.id.Info);
        Login = (Button) findViewById(R.id.login_button);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String un = Username.getText().toString();
                String pw = Password.getText().toString();
                if (un.length()==0){
                    Username.requestFocus();
                    Username.setError("FIELD CANNOT BE EMPTY");
                }
                else if(pw.length()==0){
                    Password.requestFocus();
                    Password.setError("FIELD CANNOT BE EMPTY");
                }
                else if ((un.equals("test")) && (pw.equals("1234"))) {
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Info.setText("Username or password invalid. Please try again.");
                    Info.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}