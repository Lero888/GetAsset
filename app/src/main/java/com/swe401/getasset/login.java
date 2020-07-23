package com.swe401.getasset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class login extends AppCompatActivity {

    DatabaseHelper assetDb;
    session_management session;

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;

    private static final String DB_PATH = "data/data/com.swe401.getasset/databases/getAsset.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        doDbCheck();

        assetDb = new DatabaseHelper(login.this);
        loadData();

        // find view
        Email = findViewById(R.id.login_email);
        Password = findViewById(R.id.login_password);
        Info = findViewById(R.id.Info);
        Login = findViewById(R.id.login_button);
        session = new session_management(getApplicationContext());

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pw = Password.getText().toString();
                boolean validateUser = assetDb.validateUser(email,pw);
                if (email.length()==0){
                    Email.requestFocus();
                    Email.setError("FIELD CANNOT BE EMPTY");
                }
                else if(pw.length()==0){
                    Password.requestFocus();
                    Password.setError("FIELD CANNOT BE EMPTY");
                }
                else if(validateUser) {
               // else if ((un.equals("test")) && (pw.equals("1234"))) {
                    String un = assetDb.getUserName(email,pw);
                    session.createLoginSession(un, email);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }else if (! validateUser){
                    Info.setText(R.string.invalid_login);
                    Info.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    public void onResume() {

        super.onResume();
        Email.getText().clear();
        Password.getText().clear();
    }

    private void doDbCheck() {
        try {
            File file = new File(DB_PATH);
            file.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void loadData() {

        //UserData
        assetDb.insertUserData("LEE ROU", "hello", "swe1704655@xmu.edu.my");
        assetDb.insertUserData("LIM CAROL", "hello", "swe1704205@xmu.edu.my");
        assetDb.insertUserData("a", "a", "a");

        //DepartmentData
        assetDb.insertDepartmentData("Asset", "704205");
        assetDb.insertDepartmentData("IT", "704655");

        //ItemData
        assetDb.insertItemData("Table", 100, "Classroom Table", "Table", "Asset");
        assetDb.insertItemData("Chair",  200, "Plastic Chair", "Chair", "Asset");
        assetDb.insertItemData("Microphone", 5, "Wireless Microphone (requires confirmation letter)","IT Equipment","IT");
        assetDb.insertItemData("Speaker", 2, "Portable Speaker (with microphone)","IT Equipment", "IT");


        //ItemQuantity by Date
        //20/7/2020
        assetDb.insertItemQuantityData(100, "20/07/2020", 1);
        assetDb.insertItemQuantityData(200, "20/07/2020", 2);
        assetDb.insertItemQuantityData(5, "20/07/2020", 3);
        assetDb.insertItemQuantityData(2, "20/07/2020", 4);

        //21/7/2020
        assetDb.insertItemQuantityData(100, "21/07/2020", 1);
        assetDb.insertItemQuantityData(200, "21/07/2020", 2);
        assetDb.insertItemQuantityData(5, "21/07/2020", 3);
        assetDb.insertItemQuantityData(2, "21/07/2020", 4);

        //22/7/2020
        assetDb.insertItemQuantityData(100, "22/07/2020", 1);
        assetDb.insertItemQuantityData(200, "22/07/2020", 2);
        assetDb.insertItemQuantityData(5, "22/07/2020", 3);
        assetDb.insertItemQuantityData(2, "22/07/2020", 4);

        //23/7/2020
        assetDb.insertItemQuantityData(100, "23/07/2020", 1);
        assetDb.insertItemQuantityData(200, "23/07/2020", 2);
        assetDb.insertItemQuantityData(5, "23/07/2020", 3);
        assetDb.insertItemQuantityData(2, "23/07/2020", 4);

        //24/7/2020
        assetDb.insertItemQuantityData(100, "24/07/2020", 1);
        assetDb.insertItemQuantityData(200, "24/07/2020", 2);
        assetDb.insertItemQuantityData(5, "24/07/2020", 3);
        assetDb.insertItemQuantityData(2, "24/07/2020", 4);

        //ItemBorrowData
        assetDb.insertItemBorrowData("20/07/2020", 100, "Borrowed", "60115673849", "Teachers Day Festival", "Chair", "a");

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
//
//            //22/7/2020
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
//
//            //23/7/2020
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

//            //24/7/2020
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