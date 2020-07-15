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

    DatabaseHelper assetDb;

    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        assetDb = new DatabaseHelper(login.this);
        assetDb = new DatabaseHelper(this);


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

        loadData();
    }

    public void loadData() {

        //UserData
        assetDb.insertUserData("LEE ROU", "hello", "swe1704655@xmu.edu.my");
        assetDb.insertUserData("LIM CAROL", "hello", "swe1704205@xmu.edu.my");

        //ItemData
        assetDb.insertItemData("Table", "Asset", 100, "Classroom Table", "Table");
        assetDb.insertItemData("Chair", "Asset", 200, "Plastic Chair", "Chair");
        assetDb.insertItemData("Microphone", "IT", 5, "Wireless Microphone (requires confirmation letter)", "IT Equipment");
        assetDb.insertItemData("Speaker", "IT", 2, "Portable Speaker (with microphone)", "IT Equipment");

        //ClassroomData
        assetDb.insertClassroomData("B1 101B");
        assetDb.insertClassroomData("B1 101C");
        assetDb.insertClassroomData("B1 B08");
        assetDb.insertClassroomData("B1 B09");
        assetDb.insertClassroomData("B1 B10");
        assetDb.insertClassroomData("B1 213");
        assetDb.insertClassroomData("B1 214");

        //TimeData
            //20/7/2020
        assetDb.insertTimeData("20/7/2020","9am-10am","available","1");
        assetDb.insertTimeData("20/7/2020","10am-11am","available","1");
        assetDb.insertTimeData("20/7/2020","11am-12pm","available","1");
        assetDb.insertTimeData("20/7/2020","12pm-1pm","available","1");
        assetDb.insertTimeData("20/7/2020","1pm-2pm","available","1");
        assetDb.insertTimeData("20/7/2020","2pm-3pm","available","1");
        assetDb.insertTimeData("20/7/2020","3pm-4pm","available","1");
        assetDb.insertTimeData("20/7/2020","4pm-5pm","available","1");
        assetDb.insertTimeData("20/7/2020","5pm-6pm","available","1");
        assetDb.insertTimeData("20/7/2020","6pm-7pm","available","1");
        assetDb.insertTimeData("20/7/2020","7pm-8pm","available","1");
        assetDb.insertTimeData("20/7/2020","8pm-9pm","available","1");

        assetDb.insertTimeData("20/7/2020","9am-10am","available","2");
        assetDb.insertTimeData("20/7/2020","10am-11am","available","2");
        assetDb.insertTimeData("20/7/2020","11am-12pm","available","2");
        assetDb.insertTimeData("20/7/2020","12pm-1pm","available","2");
        assetDb.insertTimeData("20/7/2020","1pm-2pm","available","2");
        assetDb.insertTimeData("20/7/2020","2pm-3pm","available","2");
        assetDb.insertTimeData("20/7/2020","3pm-4pm","available","2");
        assetDb.insertTimeData("20/7/2020","4pm-5pm","available","2");
        assetDb.insertTimeData("20/7/2020","5pm-6pm","available","2");
        assetDb.insertTimeData("20/7/2020","6pm-7pm","available","2");
        assetDb.insertTimeData("20/7/2020","7pm-8pm","available","2");
        assetDb.insertTimeData("20/7/2020","8pm-9pm","available","2");

        assetDb.insertTimeData("20/7/2020","9am-10am","available","3");
        assetDb.insertTimeData("20/7/2020","10am-11am","available","3");
        assetDb.insertTimeData("20/7/2020","11am-12pm","available","3");
        assetDb.insertTimeData("20/7/2020","12pm-1pm","available","3");
        assetDb.insertTimeData("20/7/2020","1pm-2pm","available","3");
        assetDb.insertTimeData("20/7/2020","2pm-3pm","available","3");
        assetDb.insertTimeData("20/7/2020","3pm-4pm","available","3");
        assetDb.insertTimeData("20/7/2020","4pm-5pm","available","3");
        assetDb.insertTimeData("20/7/2020","5pm-6pm","available","3");
        assetDb.insertTimeData("20/7/2020","6pm-7pm","available","3");
        assetDb.insertTimeData("20/7/2020","7pm-8pm","available","3");
        assetDb.insertTimeData("20/7/2020","8pm-9pm","available","3");

        assetDb.insertTimeData("20/7/2020","9am-10am","available","4");
        assetDb.insertTimeData("20/7/2020","10am-11am","available","4");
        assetDb.insertTimeData("20/7/2020","11am-12pm","available","4");
        assetDb.insertTimeData("20/7/2020","12pm-1pm","available","4");
        assetDb.insertTimeData("20/7/2020","1pm-2pm","available","4");
        assetDb.insertTimeData("20/7/2020","2pm-3pm","available","4");
        assetDb.insertTimeData("20/7/2020","3pm-4pm","available","4");
        assetDb.insertTimeData("20/7/2020","4pm-5pm","available","4");
        assetDb.insertTimeData("20/7/2020","5pm-6pm","available","4");
        assetDb.insertTimeData("20/7/2020","6pm-7pm","available","4");
        assetDb.insertTimeData("20/7/2020","7pm-8pm","available","4");
        assetDb.insertTimeData("20/7/2020","8pm-9pm","available","4");

        assetDb.insertTimeData("20/7/2020","9am-10am","available","5");
        assetDb.insertTimeData("20/7/2020","10am-11am","available","5");
        assetDb.insertTimeData("20/7/2020","11am-12pm","available","5");
        assetDb.insertTimeData("20/7/2020","12pm-1pm","available","5");
        assetDb.insertTimeData("20/7/2020","1pm-2pm","available","5");
        assetDb.insertTimeData("20/7/2020","2pm-3pm","available","5");
        assetDb.insertTimeData("20/7/2020","3pm-4pm","available","5");
        assetDb.insertTimeData("20/7/2020","4pm-5pm","available","5");
        assetDb.insertTimeData("20/7/2020","5pm-6pm","available","5");
        assetDb.insertTimeData("20/7/2020","6pm-7pm","available","5");
        assetDb.insertTimeData("20/7/2020","7pm-8pm","available","5");
        assetDb.insertTimeData("20/7/2020","8pm-9pm","available","5");

        assetDb.insertTimeData("20/7/2020","9am-10am","available","6");
        assetDb.insertTimeData("20/7/2020","10am-11am","available","6");
        assetDb.insertTimeData("20/7/2020","11am-12pm","available","6");
        assetDb.insertTimeData("20/7/2020","12pm-1pm","available","6");
        assetDb.insertTimeData("20/7/2020","1pm-2pm","available","6");
        assetDb.insertTimeData("20/7/2020","2pm-3pm","available","6");
        assetDb.insertTimeData("20/7/2020","3pm-4pm","available","6");
        assetDb.insertTimeData("20/7/2020","4pm-5pm","available","6");
        assetDb.insertTimeData("20/7/2020","5pm-6pm","available","6");
        assetDb.insertTimeData("20/7/2020","6pm-7pm","available","6");
        assetDb.insertTimeData("20/7/2020","7pm-8pm","available","6");
        assetDb.insertTimeData("20/7/2020","8pm-9pm","available","6");

        assetDb.insertTimeData("20/7/2020","9am-10am","available","7");
        assetDb.insertTimeData("20/7/2020","10am-11am","available","7");
        assetDb.insertTimeData("20/7/2020","11am-12pm","available","7");
        assetDb.insertTimeData("20/7/2020","12pm-1pm","available","7");
        assetDb.insertTimeData("20/7/2020","1pm-2pm","available","7");
        assetDb.insertTimeData("20/7/2020","2pm-3pm","available","7");
        assetDb.insertTimeData("20/7/2020","3pm-4pm","available","7");
        assetDb.insertTimeData("20/7/2020","4pm-5pm","available","7");
        assetDb.insertTimeData("20/7/2020","5pm-6pm","available","7");
        assetDb.insertTimeData("20/7/2020","6pm-7pm","available","7");
        assetDb.insertTimeData("20/7/2020","7pm-8pm","available","7");
        assetDb.insertTimeData("20/7/2020","8pm-9pm","available","7");

            //21/7/2020
        assetDb.insertTimeData("21/7/2020","9am-10am","available","1");
        assetDb.insertTimeData("21/7/2020","10am-11am","available","1");
        assetDb.insertTimeData("21/7/2020","11am-12pm","available","1");
        assetDb.insertTimeData("21/7/2020","12pm-1pm","available","1");
        assetDb.insertTimeData("21/7/2020","1pm-2pm","available","1");
        assetDb.insertTimeData("21/7/2020","2pm-3pm","available","1");
        assetDb.insertTimeData("21/7/2020","3pm-4pm","available","1");
        assetDb.insertTimeData("21/7/2020","4pm-5pm","available","1");
        assetDb.insertTimeData("21/7/2020","5pm-6pm","available","1");
        assetDb.insertTimeData("21/7/2020","6pm-7pm","available","1");
        assetDb.insertTimeData("21/7/2020","7pm-8pm","available","1");
        assetDb.insertTimeData("21/7/2020","8pm-9pm","available","1");

        assetDb.insertTimeData("21/7/2020","9am-10am","available","2");
        assetDb.insertTimeData("21/7/2020","10am-11am","available","2");
        assetDb.insertTimeData("21/7/2020","11am-12pm","available","2");
        assetDb.insertTimeData("21/7/2020","12pm-1pm","available","2");
        assetDb.insertTimeData("21/7/2020","1pm-2pm","available","2");
        assetDb.insertTimeData("21/7/2020","2pm-3pm","available","2");
        assetDb.insertTimeData("21/7/2020","3pm-4pm","available","2");
        assetDb.insertTimeData("21/7/2020","4pm-5pm","available","2");
        assetDb.insertTimeData("21/7/2020","5pm-6pm","available","2");
        assetDb.insertTimeData("21/7/2020","6pm-7pm","available","2");
        assetDb.insertTimeData("21/7/2020","7pm-8pm","available","2");
        assetDb.insertTimeData("21/7/2020","8pm-9pm","available","2");

        assetDb.insertTimeData("21/7/2020","9am-10am","available","3");
        assetDb.insertTimeData("21/7/2020","10am-11am","available","3");
        assetDb.insertTimeData("21/7/2020","11am-12pm","available","3");
        assetDb.insertTimeData("21/7/2020","12pm-1pm","available","3");
        assetDb.insertTimeData("21/7/2020","1pm-2pm","available","3");
        assetDb.insertTimeData("21/7/2020","2pm-3pm","available","3");
        assetDb.insertTimeData("21/7/2020","3pm-4pm","available","3");
        assetDb.insertTimeData("21/7/2020","4pm-5pm","available","3");
        assetDb.insertTimeData("21/7/2020","5pm-6pm","available","3");
        assetDb.insertTimeData("21/7/2020","6pm-7pm","available","3");
        assetDb.insertTimeData("21/7/2020","7pm-8pm","available","3");
        assetDb.insertTimeData("21/7/2020","8pm-9pm","available","3");

        assetDb.insertTimeData("21/7/2020","9am-10am","available","4");
        assetDb.insertTimeData("21/7/2020","10am-11am","available","4");
        assetDb.insertTimeData("21/7/2020","11am-12pm","available","4");
        assetDb.insertTimeData("21/7/2020","12pm-1pm","available","4");
        assetDb.insertTimeData("21/7/2020","1pm-2pm","available","4");
        assetDb.insertTimeData("21/7/2020","2pm-3pm","available","4");
        assetDb.insertTimeData("21/7/2020","3pm-4pm","available","4");
        assetDb.insertTimeData("21/7/2020","4pm-5pm","available","4");
        assetDb.insertTimeData("21/7/2020","5pm-6pm","available","4");
        assetDb.insertTimeData("21/7/2020","6pm-7pm","available","4");
        assetDb.insertTimeData("21/7/2020","7pm-8pm","available","4");
        assetDb.insertTimeData("21/7/2020","8pm-9pm","available","4");

        assetDb.insertTimeData("21/7/2020","9am-10am","available","5");
        assetDb.insertTimeData("21/7/2020","10am-11am","available","5");
        assetDb.insertTimeData("21/7/2020","11am-12pm","available","5");
        assetDb.insertTimeData("21/7/2020","12pm-1pm","available","5");
        assetDb.insertTimeData("21/7/2020","1pm-2pm","available","5");
        assetDb.insertTimeData("21/7/2020","2pm-3pm","available","5");
        assetDb.insertTimeData("21/7/2020","3pm-4pm","available","5");
        assetDb.insertTimeData("21/7/2020","4pm-5pm","available","5");
        assetDb.insertTimeData("21/7/2020","5pm-6pm","available","5");
        assetDb.insertTimeData("21/7/2020","6pm-7pm","available","5");
        assetDb.insertTimeData("21/7/2020","7pm-8pm","available","5");
        assetDb.insertTimeData("21/7/2020","8pm-9pm","available","5");

        assetDb.insertTimeData("21/7/2020","9am-10am","available","6");
        assetDb.insertTimeData("21/7/2020","10am-11am","available","6");
        assetDb.insertTimeData("21/7/2020","11am-12pm","available","6");
        assetDb.insertTimeData("21/7/2020","12pm-1pm","available","6");
        assetDb.insertTimeData("21/7/2020","1pm-2pm","available","6");
        assetDb.insertTimeData("21/7/2020","2pm-3pm","available","6");
        assetDb.insertTimeData("21/7/2020","3pm-4pm","available","6");
        assetDb.insertTimeData("21/7/2020","4pm-5pm","available","6");
        assetDb.insertTimeData("21/7/2020","5pm-6pm","available","6");
        assetDb.insertTimeData("21/7/2020","6pm-7pm","available","6");
        assetDb.insertTimeData("21/7/2020","7pm-8pm","available","6");
        assetDb.insertTimeData("21/7/2020","8pm-9pm","available","6");

        assetDb.insertTimeData("21/7/2020","9am-10am","available","7");
        assetDb.insertTimeData("21/7/2020","10am-11am","available","7");
        assetDb.insertTimeData("21/7/2020","11am-12pm","available","7");
        assetDb.insertTimeData("21/7/2020","12pm-1pm","available","7");
        assetDb.insertTimeData("21/7/2020","1pm-2pm","available","7");
        assetDb.insertTimeData("21/7/2020","2pm-3pm","available","7");
        assetDb.insertTimeData("21/7/2020","3pm-4pm","available","7");
        assetDb.insertTimeData("21/7/2020","4pm-5pm","available","7");
        assetDb.insertTimeData("21/7/2020","5pm-6pm","available","7");
        assetDb.insertTimeData("21/7/2020","6pm-7pm","available","7");
        assetDb.insertTimeData("21/7/2020","7pm-8pm","available","7");
        assetDb.insertTimeData("21/7/2020","8pm-9pm","available","7");

            //22/7/2020
        assetDb.insertTimeData("22/7/2020","9am-10am","available","1");
        assetDb.insertTimeData("22/7/2020","10am-11am","available","1");
        assetDb.insertTimeData("22/7/2020","11am-12pm","available","1");
        assetDb.insertTimeData("22/7/2020","12pm-1pm","available","1");
        assetDb.insertTimeData("22/7/2020","1pm-2pm","available","1");
        assetDb.insertTimeData("22/7/2020","2pm-3pm","available","1");
        assetDb.insertTimeData("22/7/2020","3pm-4pm","available","1");
        assetDb.insertTimeData("22/7/2020","4pm-5pm","available","1");
        assetDb.insertTimeData("22/7/2020","5pm-6pm","available","1");
        assetDb.insertTimeData("22/7/2020","6pm-7pm","available","1");
        assetDb.insertTimeData("22/7/2020","7pm-8pm","available","1");
        assetDb.insertTimeData("22/7/2020","8pm-9pm","available","1");

        assetDb.insertTimeData("22/7/2020","9am-10am","available","2");
        assetDb.insertTimeData("22/7/2020","10am-11am","available","2");
        assetDb.insertTimeData("22/7/2020","11am-12pm","available","2");
        assetDb.insertTimeData("22/7/2020","12pm-1pm","available","2");
        assetDb.insertTimeData("22/7/2020","1pm-2pm","available","2");
        assetDb.insertTimeData("22/7/2020","2pm-3pm","available","2");
        assetDb.insertTimeData("22/7/2020","3pm-4pm","available","2");
        assetDb.insertTimeData("22/7/2020","4pm-5pm","available","2");
        assetDb.insertTimeData("22/7/2020","5pm-6pm","available","2");
        assetDb.insertTimeData("22/7/2020","6pm-7pm","available","2");
        assetDb.insertTimeData("22/7/2020","7pm-8pm","available","2");
        assetDb.insertTimeData("22/7/2020","8pm-9pm","available","2");

        assetDb.insertTimeData("22/7/2020","9am-10am","available","3");
        assetDb.insertTimeData("22/7/2020","10am-11am","available","3");
        assetDb.insertTimeData("22/7/2020","11am-12pm","available","3");
        assetDb.insertTimeData("22/7/2020","12pm-1pm","available","3");
        assetDb.insertTimeData("22/7/2020","1pm-2pm","available","3");
        assetDb.insertTimeData("22/7/2020","2pm-3pm","available","3");
        assetDb.insertTimeData("22/7/2020","3pm-4pm","available","3");
        assetDb.insertTimeData("22/7/2020","4pm-5pm","available","3");
        assetDb.insertTimeData("22/7/2020","5pm-6pm","available","3");
        assetDb.insertTimeData("22/7/2020","6pm-7pm","available","3");
        assetDb.insertTimeData("22/7/2020","7pm-8pm","available","3");
        assetDb.insertTimeData("22/7/2020","8pm-9pm","available","3");

        assetDb.insertTimeData("22/7/2020","9am-10am","available","4");
        assetDb.insertTimeData("22/7/2020","10am-11am","available","4");
        assetDb.insertTimeData("22/7/2020","11am-12pm","available","4");
        assetDb.insertTimeData("22/7/2020","12pm-1pm","available","4");
        assetDb.insertTimeData("22/7/2020","1pm-2pm","available","4");
        assetDb.insertTimeData("22/7/2020","2pm-3pm","available","4");
        assetDb.insertTimeData("22/7/2020","3pm-4pm","available","4");
        assetDb.insertTimeData("22/7/2020","4pm-5pm","available","4");
        assetDb.insertTimeData("22/7/2020","5pm-6pm","available","4");
        assetDb.insertTimeData("22/7/2020","6pm-7pm","available","4");
        assetDb.insertTimeData("22/7/2020","7pm-8pm","available","4");
        assetDb.insertTimeData("22/7/2020","8pm-9pm","available","4");

        assetDb.insertTimeData("22/7/2020","9am-10am","available","5");
        assetDb.insertTimeData("22/7/2020","10am-11am","available","5");
        assetDb.insertTimeData("22/7/2020","11am-12pm","available","5");
        assetDb.insertTimeData("22/7/2020","12pm-1pm","available","5");
        assetDb.insertTimeData("22/7/2020","1pm-2pm","available","5");
        assetDb.insertTimeData("22/7/2020","2pm-3pm","available","5");
        assetDb.insertTimeData("22/7/2020","3pm-4pm","available","5");
        assetDb.insertTimeData("22/7/2020","4pm-5pm","available","5");
        assetDb.insertTimeData("22/7/2020","5pm-6pm","available","5");
        assetDb.insertTimeData("22/7/2020","6pm-7pm","available","5");
        assetDb.insertTimeData("22/7/2020","7pm-8pm","available","5");
        assetDb.insertTimeData("22/7/2020","8pm-9pm","available","5");

        assetDb.insertTimeData("22/7/2020","9am-10am","available","6");
        assetDb.insertTimeData("22/7/2020","10am-11am","available","6");
        assetDb.insertTimeData("22/7/2020","11am-12pm","available","6");
        assetDb.insertTimeData("22/7/2020","12pm-1pm","available","6");
        assetDb.insertTimeData("22/7/2020","1pm-2pm","available","6");
        assetDb.insertTimeData("22/7/2020","2pm-3pm","available","6");
        assetDb.insertTimeData("22/7/2020","3pm-4pm","available","6");
        assetDb.insertTimeData("22/7/2020","4pm-5pm","available","6");
        assetDb.insertTimeData("22/7/2020","5pm-6pm","available","6");
        assetDb.insertTimeData("22/7/2020","6pm-7pm","available","6");
        assetDb.insertTimeData("22/7/2020","7pm-8pm","available","6");
        assetDb.insertTimeData("22/7/2020","8pm-9pm","available","6");

        assetDb.insertTimeData("22/7/2020","9am-10am","available","7");
        assetDb.insertTimeData("22/7/2020","10am-11am","available","7");
        assetDb.insertTimeData("22/7/2020","11am-12pm","available","7");
        assetDb.insertTimeData("22/7/2020","12pm-1pm","available","7");
        assetDb.insertTimeData("22/7/2020","1pm-2pm","available","7");
        assetDb.insertTimeData("22/7/2020","2pm-3pm","available","7");
        assetDb.insertTimeData("22/7/2020","3pm-4pm","available","7");
        assetDb.insertTimeData("22/7/2020","4pm-5pm","available","7");
        assetDb.insertTimeData("22/7/2020","5pm-6pm","available","7");
        assetDb.insertTimeData("22/7/2020","6pm-7pm","available","7");
        assetDb.insertTimeData("22/7/2020","7pm-8pm","available","7");
        assetDb.insertTimeData("22/7/2020","8pm-9pm","available","7");

            //23/7/2020
        assetDb.insertTimeData("23/7/2020","9am-10am","available","1");
        assetDb.insertTimeData("23/7/2020","10am-11am","available","1");
        assetDb.insertTimeData("23/7/2020","11am-12pm","available","1");
        assetDb.insertTimeData("23/7/2020","12pm-1pm","available","1");
        assetDb.insertTimeData("23/7/2020","1pm-2pm","available","1");
        assetDb.insertTimeData("23/7/2020","2pm-3pm","available","1");
        assetDb.insertTimeData("23/7/2020","3pm-4pm","available","1");
        assetDb.insertTimeData("23/7/2020","4pm-5pm","available","1");
        assetDb.insertTimeData("23/7/2020","5pm-6pm","available","1");
        assetDb.insertTimeData("23/7/2020","6pm-7pm","available","1");
        assetDb.insertTimeData("23/7/2020","7pm-8pm","available","1");
        assetDb.insertTimeData("23/7/2020","8pm-9pm","available","1");

        assetDb.insertTimeData("23/7/2020","9am-10am","available","2");
        assetDb.insertTimeData("23/7/2020","10am-11am","available","2");
        assetDb.insertTimeData("23/7/2020","11am-12pm","available","2");
        assetDb.insertTimeData("23/7/2020","12pm-1pm","available","2");
        assetDb.insertTimeData("23/7/2020","1pm-2pm","available","2");
        assetDb.insertTimeData("23/7/2020","2pm-3pm","available","2");
        assetDb.insertTimeData("23/7/2020","3pm-4pm","available","2");
        assetDb.insertTimeData("23/7/2020","4pm-5pm","available","2");
        assetDb.insertTimeData("23/7/2020","5pm-6pm","available","2");
        assetDb.insertTimeData("23/7/2020","6pm-7pm","available","2");
        assetDb.insertTimeData("23/7/2020","7pm-8pm","available","2");
        assetDb.insertTimeData("23/7/2020","8pm-9pm","available","2");

        assetDb.insertTimeData("23/7/2020","9am-10am","available","3");
        assetDb.insertTimeData("23/7/2020","10am-11am","available","3");
        assetDb.insertTimeData("23/7/2020","11am-12pm","available","3");
        assetDb.insertTimeData("23/7/2020","12pm-1pm","available","3");
        assetDb.insertTimeData("23/7/2020","1pm-2pm","available","3");
        assetDb.insertTimeData("23/7/2020","2pm-3pm","available","3");
        assetDb.insertTimeData("23/7/2020","3pm-4pm","available","3");
        assetDb.insertTimeData("23/7/2020","4pm-5pm","available","3");
        assetDb.insertTimeData("23/7/2020","5pm-6pm","available","3");
        assetDb.insertTimeData("23/7/2020","6pm-7pm","available","3");
        assetDb.insertTimeData("23/7/2020","7pm-8pm","available","3");
        assetDb.insertTimeData("23/7/2020","8pm-9pm","available","3");

        assetDb.insertTimeData("23/7/2020","9am-10am","available","4");
        assetDb.insertTimeData("23/7/2020","10am-11am","available","4");
        assetDb.insertTimeData("23/7/2020","11am-12pm","available","4");
        assetDb.insertTimeData("23/7/2020","12pm-1pm","available","4");
        assetDb.insertTimeData("23/7/2020","1pm-2pm","available","4");
        assetDb.insertTimeData("23/7/2020","2pm-3pm","available","4");
        assetDb.insertTimeData("23/7/2020","3pm-4pm","available","4");
        assetDb.insertTimeData("23/7/2020","4pm-5pm","available","4");
        assetDb.insertTimeData("23/7/2020","5pm-6pm","available","4");
        assetDb.insertTimeData("23/7/2020","6pm-7pm","available","4");
        assetDb.insertTimeData("23/7/2020","7pm-8pm","available","4");
        assetDb.insertTimeData("23/7/2020","8pm-9pm","available","4");

        assetDb.insertTimeData("23/7/2020","9am-10am","available","5");
        assetDb.insertTimeData("23/7/2020","10am-11am","available","5");
        assetDb.insertTimeData("23/7/2020","11am-12pm","available","5");
        assetDb.insertTimeData("23/7/2020","12pm-1pm","available","5");
        assetDb.insertTimeData("23/7/2020","1pm-2pm","available","5");
        assetDb.insertTimeData("23/7/2020","2pm-3pm","available","5");
        assetDb.insertTimeData("23/7/2020","3pm-4pm","available","5");
        assetDb.insertTimeData("23/7/2020","4pm-5pm","available","5");
        assetDb.insertTimeData("23/7/2020","5pm-6pm","available","5");
        assetDb.insertTimeData("23/7/2020","6pm-7pm","available","5");
        assetDb.insertTimeData("23/7/2020","7pm-8pm","available","5");
        assetDb.insertTimeData("23/7/2020","8pm-9pm","available","5");

        assetDb.insertTimeData("23/7/2020","9am-10am","available","6");
        assetDb.insertTimeData("23/7/2020","10am-11am","available","6");
        assetDb.insertTimeData("23/7/2020","11am-12pm","available","6");
        assetDb.insertTimeData("23/7/2020","12pm-1pm","available","6");
        assetDb.insertTimeData("23/7/2020","1pm-2pm","available","6");
        assetDb.insertTimeData("23/7/2020","2pm-3pm","available","6");
        assetDb.insertTimeData("23/7/2020","3pm-4pm","available","6");
        assetDb.insertTimeData("23/7/2020","4pm-5pm","available","6");
        assetDb.insertTimeData("23/7/2020","5pm-6pm","available","6");
        assetDb.insertTimeData("23/7/2020","6pm-7pm","available","6");
        assetDb.insertTimeData("23/7/2020","7pm-8pm","available","6");
        assetDb.insertTimeData("23/7/2020","8pm-9pm","available","6");

        assetDb.insertTimeData("23/7/2020","9am-10am","available","7");
        assetDb.insertTimeData("23/7/2020","10am-11am","available","7");
        assetDb.insertTimeData("23/7/2020","11am-12pm","available","7");
        assetDb.insertTimeData("23/7/2020","12pm-1pm","available","7");
        assetDb.insertTimeData("23/7/2020","1pm-2pm","available","7");
        assetDb.insertTimeData("23/7/2020","2pm-3pm","available","7");
        assetDb.insertTimeData("23/7/2020","3pm-4pm","available","7");
        assetDb.insertTimeData("23/7/2020","4pm-5pm","available","7");
        assetDb.insertTimeData("23/7/2020","5pm-6pm","available","7");
        assetDb.insertTimeData("23/7/2020","6pm-7pm","available","7");
        assetDb.insertTimeData("23/7/2020","7pm-8pm","available","7");
        assetDb.insertTimeData("23/7/2020","8pm-9pm","available","7");

            //24/7/2020
        assetDb.insertTimeData("24/7/2020","9am-10am","available","1");
        assetDb.insertTimeData("24/7/2020","10am-11am","available","1");
        assetDb.insertTimeData("24/7/2020","11am-12pm","available","1");
        assetDb.insertTimeData("24/7/2020","12pm-1pm","available","1");
        assetDb.insertTimeData("24/7/2020","1pm-2pm","available","1");
        assetDb.insertTimeData("24/7/2020","2pm-3pm","available","1");
        assetDb.insertTimeData("24/7/2020","3pm-4pm","available","1");
        assetDb.insertTimeData("24/7/2020","4pm-5pm","available","1");
        assetDb.insertTimeData("24/7/2020","5pm-6pm","available","1");
        assetDb.insertTimeData("24/7/2020","6pm-7pm","available","1");
        assetDb.insertTimeData("24/7/2020","7pm-8pm","available","1");
        assetDb.insertTimeData("24/7/2020","8pm-9pm","available","1");

        assetDb.insertTimeData("24/7/2020","9am-10am","available","2");
        assetDb.insertTimeData("24/7/2020","10am-11am","available","2");
        assetDb.insertTimeData("24/7/2020","11am-12pm","available","2");
        assetDb.insertTimeData("24/7/2020","12pm-1pm","available","2");
        assetDb.insertTimeData("24/7/2020","1pm-2pm","available","2");
        assetDb.insertTimeData("24/7/2020","2pm-3pm","available","2");
        assetDb.insertTimeData("24/7/2020","3pm-4pm","available","2");
        assetDb.insertTimeData("24/7/2020","4pm-5pm","available","2");
        assetDb.insertTimeData("24/7/2020","5pm-6pm","available","2");
        assetDb.insertTimeData("24/7/2020","6pm-7pm","available","2");
        assetDb.insertTimeData("24/7/2020","7pm-8pm","available","2");
        assetDb.insertTimeData("24/7/2020","8pm-9pm","available","2");

        assetDb.insertTimeData("24/7/2020","9am-10am","available","3");
        assetDb.insertTimeData("24/7/2020","10am-11am","available","3");
        assetDb.insertTimeData("24/7/2020","11am-12pm","available","3");
        assetDb.insertTimeData("24/7/2020","12pm-1pm","available","3");
        assetDb.insertTimeData("24/7/2020","1pm-2pm","available","3");
        assetDb.insertTimeData("24/7/2020","2pm-3pm","available","3");
        assetDb.insertTimeData("24/7/2020","3pm-4pm","available","3");
        assetDb.insertTimeData("24/7/2020","4pm-5pm","available","3");
        assetDb.insertTimeData("24/7/2020","5pm-6pm","available","3");
        assetDb.insertTimeData("24/7/2020","6pm-7pm","available","3");
        assetDb.insertTimeData("24/7/2020","7pm-8pm","available","3");
        assetDb.insertTimeData("24/7/2020","8pm-9pm","available","3");

        assetDb.insertTimeData("24/7/2020","9am-10am","available","4");
        assetDb.insertTimeData("24/7/2020","10am-11am","available","4");
        assetDb.insertTimeData("24/7/2020","11am-12pm","available","4");
        assetDb.insertTimeData("24/7/2020","12pm-1pm","available","4");
        assetDb.insertTimeData("24/7/2020","1pm-2pm","available","4");
        assetDb.insertTimeData("24/7/2020","2pm-3pm","available","4");
        assetDb.insertTimeData("24/7/2020","3pm-4pm","available","4");
        assetDb.insertTimeData("24/7/2020","4pm-5pm","available","4");
        assetDb.insertTimeData("24/7/2020","5pm-6pm","available","4");
        assetDb.insertTimeData("24/7/2020","6pm-7pm","available","4");
        assetDb.insertTimeData("24/7/2020","7pm-8pm","available","4");
        assetDb.insertTimeData("24/7/2020","8pm-9pm","available","4");

        assetDb.insertTimeData("24/7/2020","9am-10am","available","5");
        assetDb.insertTimeData("24/7/2020","10am-11am","available","5");
        assetDb.insertTimeData("24/7/2020","11am-12pm","available","5");
        assetDb.insertTimeData("24/7/2020","12pm-1pm","available","5");
        assetDb.insertTimeData("24/7/2020","1pm-2pm","available","5");
        assetDb.insertTimeData("24/7/2020","2pm-3pm","available","5");
        assetDb.insertTimeData("24/7/2020","3pm-4pm","available","5");
        assetDb.insertTimeData("24/7/2020","4pm-5pm","available","5");
        assetDb.insertTimeData("24/7/2020","5pm-6pm","available","5");
        assetDb.insertTimeData("24/7/2020","6pm-7pm","available","5");
        assetDb.insertTimeData("24/7/2020","7pm-8pm","available","5");
        assetDb.insertTimeData("24/7/2020","8pm-9pm","available","5");

        assetDb.insertTimeData("24/7/2020","9am-10am","available","6");
        assetDb.insertTimeData("24/7/2020","10am-11am","available","6");
        assetDb.insertTimeData("24/7/2020","11am-12pm","available","6");
        assetDb.insertTimeData("24/7/2020","12pm-1pm","available","6");
        assetDb.insertTimeData("24/7/2020","1pm-2pm","available","6");
        assetDb.insertTimeData("24/7/2020","2pm-3pm","available","6");
        assetDb.insertTimeData("24/7/2020","3pm-4pm","available","6");
        assetDb.insertTimeData("24/7/2020","4pm-5pm","available","6");
        assetDb.insertTimeData("24/7/2020","5pm-6pm","available","6");
        assetDb.insertTimeData("24/7/2020","6pm-7pm","available","6");
        assetDb.insertTimeData("24/7/2020","7pm-8pm","available","6");
        assetDb.insertTimeData("24/7/2020","8pm-9pm","available","6");

        assetDb.insertTimeData("24/7/2020","9am-10am","available","7");
        assetDb.insertTimeData("24/7/2020","10am-11am","available","7");
        assetDb.insertTimeData("24/7/2020","11am-12pm","available","7");
        assetDb.insertTimeData("24/7/2020","12pm-1pm","available","7");
        assetDb.insertTimeData("24/7/2020","1pm-2pm","available","7");
        assetDb.insertTimeData("24/7/2020","2pm-3pm","available","7");
        assetDb.insertTimeData("24/7/2020","3pm-4pm","available","7");
        assetDb.insertTimeData("24/7/2020","4pm-5pm","available","7");
        assetDb.insertTimeData("24/7/2020","5pm-6pm","available","7");
        assetDb.insertTimeData("24/7/2020","6pm-7pm","available","7");
        assetDb.insertTimeData("24/7/2020","7pm-8pm","available","7");
        assetDb.insertTimeData("24/7/2020","8pm-9pm","available","7");
    }
}