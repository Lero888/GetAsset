package com.swe401.getasset;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.database.Cursor;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_item_reservation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_item_reservation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper assetDb = new DatabaseHelper(getActivity());
    Fragment fragment;
    private ImageButton buttonTable;
    private ImageButton buttonChair;
    private ImageButton buttonIT;
    private TextView textViewTable;
    private TextView textViewChair;

    public fragment_item_reservation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment activity_fragment_item_reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_item_reservation newInstance(String param1, String param2) {
        fragment_item_reservation fragment = new fragment_item_reservation();
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
        final View view  = inflater.inflate(R.layout.activity_fragment_item_reservation, container, false);

        // find view
        buttonTable = (ImageButton) view.findViewById(R.id.button_table);
        buttonChair = (ImageButton) view.findViewById(R.id.button_chair);
        buttonIT = (ImageButton) view.findViewById(R.id.button_IT);
        textViewTable = (TextView) view.findViewById(R.id.TableAmount);
        textViewChair = (TextView) view.findViewById(R.id.ChairAmount);

        // get Quantity of Table and Chair
        Cursor res = assetDb.fetchItemData("Table");
        if (res.getCount() == 0) {

            // Show message
        } else {
            textViewTable.setText(res.getInt(3));
        }

        res = assetDb.fetchItemData("Chair");
        textViewChair.setText(res.getInt(3));

        buttonTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Pass message
                Bundle bundle = new Bundle();
                bundle.putString("item","table");

                fragment = new fragment_item_reservation_details_one();
                fragment.setArguments(bundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("fragment_item_reservation");
                ft.commit();
            }
        });


        // go to IT fragment
        buttonIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new fragment_item_reservation_it();
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