package com.swe401.getasset;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_item_reservation_details_one#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_item_reservation_details_one extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper assetDb;
    Fragment fragment;
    private Button book;
    private ImageView img;
    private TextView itemName;
    private TextView departmentName;
    private TextView description;
    private TextView itemQuantity;
    private CalendarView calendarView;

    public fragment_item_reservation_details_one() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_item_reservation_details_one.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_item_reservation_details_one newInstance(String param1, String param2) {
        fragment_item_reservation_details_one fragment = new fragment_item_reservation_details_one();
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
        final View view = inflater.inflate(R.layout.fragment_item_reservation_details_one, container, false);
        assetDb = new DatabaseHelper(getActivity());

        // find View
        book = view.findViewById(R.id.button_book_item);
        img = view.findViewById(R.id.image_item);
        itemName = view.findViewById(R.id.itemName2);
        departmentName = view.findViewById(R.id.itemDepartment2);
        description = view.findViewById(R.id.itemDesc2);
        itemQuantity = view.findViewById(R.id.itemAmountDate);
        calendarView = view.findViewById(R.id.calendarView);

        Bundle bundle = this.getArguments();
        String item = "";

        if(bundle != null){
            item = bundle.getString("item");
        }

        Cursor res = assetDb.fetchItemData(item);

        if (item.equals("Table")) {

            img.setImageResource(R.drawable.table);
        } else if (item.equals("Chair")) {
            img.setImageResource(R.drawable.chair);

        } else if (item.equals("Microphone")) {
            img.setImageResource(R.drawable.microphone);
        } else {
            img.setImageResource(R.drawable.speaker);
        }

        if (res.getCount() == 0) {

            Log.v("Error", "No value is found");
            // Show message
        } else {
            itemName.setText(res.getString(1));
            departmentName.setText(res.getString(2));
            description.setText(res.getString(4));
        }

        calendarView.setMinDate(System.currentTimeMillis() - 1000);

        final String finalItem1 = item;
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month+=1;
                String date = dayOfMonth + "/" + month + "/" + year;

                Cursor res = assetDb.fetchItemQuantityData(finalItem1, date);
                if (res.getCount() == 0) {

                    Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();

                    // Show message
                } else {
                    itemQuantity.setText(String.valueOf(res.getInt(1)));
                }

            }
        });


        // go to booking fragment
        final String finalItem = item;
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("item", finalItem);

                fragment = new fragment_item_reservation_details_two();
                fragment.setArguments(bundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("fragment_item_reservation_details_one");
                ft.commit();
            }
        });

        return view;
    }
}