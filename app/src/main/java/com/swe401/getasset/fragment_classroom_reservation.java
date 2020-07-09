package com.swe401.getasset;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_item_reservation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_classroom_reservation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView info;
    private Button book;

    public fragment_classroom_reservation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment activity_fragment_classroom_reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_classroom_reservation newInstance(String param1, String param2) {
        fragment_classroom_reservation fragment = new fragment_classroom_reservation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




//        coffeeType = (ImageView) findViewById(R.id.imageView3);
//coffeeType.setImageResource(R.drawable.cappuccino);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       final View view = inflater.inflate(R.layout.activity_fragment_classroom_reservation, container, false);
        info = (TextView) view.findViewById(R.id.Info);
        book = (Button) view.findViewById(R.id.button_book);
        final String[] selectedDate = new String[1];
        final String[] selectedTime = new String[1];
        final String[] selectedRoom = new String[1];
        Spinner mySpinner_date = view.findViewById(R.id.spinner_Date);
        ArrayAdapter<String> myAdapter_date = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.dates));
        myAdapter_date.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner_date.setAdapter(myAdapter_date);
        mySpinner_date.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate[0] = adapterView.getItemAtPosition(i).toString();
                Log.d(selectedDate[0],"test");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner mySpinner_time = view.findViewById(R.id.spinner_time);
        ArrayAdapter<String> myAdapter_time = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.time));
        myAdapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner_time.setAdapter(myAdapter_time);
        mySpinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTime[0] = adapterView.getItemAtPosition(i).toString();
                Log.d(selectedTime[0],"test");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner mySpinner_type = view.findViewById(R.id.spinner_Type);
        ArrayAdapter<String> myAdapter_type = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.classroom_type));
        myAdapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner_type.setAdapter(myAdapter_type);



        final int[] pos = new int[1];
        mySpinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pos[0] =i;
                add();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            private void add(){
                switch(pos[0]) {
                    case 0:
                        Spinner mySpinner_no = view.findViewById(R.id.spinner_No);
                        ArrayAdapter<String> myAdapter_no = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.classroom_No1));
                        myAdapter_no.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mySpinner_no.setAdapter(myAdapter_no);
                        mySpinner_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                selectedRoom[0] = adapterView.getItemAtPosition(i).toString();
                                Log.d(selectedRoom[0],"test");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;

                    case 1:
                        mySpinner_no = view.findViewById(R.id.spinner_No);
                        myAdapter_no = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classroom_No2));
                        myAdapter_no.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mySpinner_no.setAdapter(myAdapter_no);
                        mySpinner_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                selectedRoom[0] = adapterView.getItemAtPosition(i).toString();
                                Log.d(selectedRoom[0],"test");
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                }
            }


        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), fragment_room_reservation_details.class);
                startActivity(intent);
            }
        });

        return view;
    }


}