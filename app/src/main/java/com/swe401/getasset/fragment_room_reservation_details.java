package com.swe401.getasset;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_item_reservation_it#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_room_reservation_details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper assetDb;
    session_management session;


    public fragment_room_reservation_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_item_reservation_it.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_room_reservation_details newInstance(String param1, String param2) {
        fragment_room_reservation_details fragment = new fragment_room_reservation_details();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.activity_fragment_room_reservation_details, container, false);
        session = new session_management(getActivity().getApplicationContext());
        session.checkLogin();
        assetDb = new DatabaseHelper(getActivity());
        HashMap<String, String> user = session.getUserDetails();

        final String username = user.get(session_management.KEY_NAME);
        EditText room_no = (EditText)view.findViewById(R.id.editTextNo);
        EditText room_date = (EditText)view.findViewById(R.id.editTextDate);
        EditText room_time = (EditText)view.findViewById(R.id.editTextTime);
        final EditText room_phoneNo = view.findViewById(R.id.editTextPhone);
        final EditText room_usage = view.findViewById(R.id.editTextRoomUsage);
        Button button_cancel = (Button)view.findViewById(R.id.button_cancel);
        Button button_save = (Button)view.findViewById(R.id.button_save);
        final String rNo = getArguments().getString("Room");
        final String rDate = getArguments().getString("Date");
        final String rTime = getArguments().getString("Time");
        room_no.setText(rNo);
        room_date.setText(rDate);
        room_time.setText(rTime);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telNo = room_phoneNo.getText().toString();
                String roomUsage = room_usage.getText().toString();
                if (telNo.length()==0 || roomUsage.length()==0){
                    if (telNo.length()==0){
                        room_phoneNo.requestFocus();
                        room_phoneNo.setError("Phone number is required");
                    }else if (roomUsage.length()==0){
                        room_usage.requestFocus();
                        room_usage.setError("Usage is required");
                    }

                }else{
                    assetDb.insertRoomBorrow(rDate,rTime,rNo,telNo,roomUsage,username);
                    Toast.makeText(getContext(),"Book Successful", Toast.LENGTH_SHORT).show();
                    fragment_status nextFrag = new fragment_status();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, nextFrag)
                            .addToBackStack(null)
                            .commit();
                }

            }


        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_classroom_reservation nextFrag = new fragment_classroom_reservation();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }

        });
        return view;
    }
}