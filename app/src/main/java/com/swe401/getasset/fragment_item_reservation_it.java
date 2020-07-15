package com.swe401.getasset;

import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_item_reservation_it#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_item_reservation_it extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper assetDb;
    Fragment fragment;
    private ImageButton buttonSpeaker;
    private ImageButton buttonMicrophone;
    private TextView quantitySpeaker;
    private TextView quantityMicrophone;

    public fragment_item_reservation_it() {
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
    public static fragment_item_reservation_it newInstance(String param1, String param2) {
        fragment_item_reservation_it fragment = new fragment_item_reservation_it();
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
        assetDb = new DatabaseHelper(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_item_reservation_it, container, false);

        buttonSpeaker = (ImageButton) view.findViewById(R.id.button_speaker);
        buttonMicrophone = (ImageButton) view.findViewById(R.id.button_mic);
        quantitySpeaker = (TextView) view.findViewById(R.id.speakerAmount);
        quantityMicrophone = (TextView) view.findViewById(R.id.micAmount);

        // get Quantity of Speaker and Mic
        Cursor res = assetDb.fetchItemData("Speaker");
        quantitySpeaker.setText(String.valueOf(res.getInt(3)));

        res = assetDb.fetchItemData("Microphone");
        quantityMicrophone.setText(String.valueOf(res.getInt(3)));

        buttonSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Pass message
                Bundle bundle = new Bundle();
                bundle.putString("item","Speaker");

                fragment = new fragment_item_reservation_details_one();
                fragment.setArguments(bundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("fragment_item_reservation");
                ft.commit();
            }
        });

        buttonMicrophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Pass message
                Bundle bundle = new Bundle();
                bundle.putString("item","Microphone");

                fragment = new fragment_item_reservation_details_one();
                fragment.setArguments(bundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("fragment_item_reservation");
                ft.commit();
            }
        });

        return view;
    }
}