package com.swe401.getasset;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_item_reservation_details_two#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_item_reservation_details_two extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper assetDb;
    session_management session;
    Fragment fragment;

    private Button save;
    private Button cancel;
    private ImageView img;
    private TextView itemName;
    private TextView departmentName;
    private TextView description;

    private EditText phoneNo;
    private Spinner spinnerDate;
    private String dateSelected;
    private EditText itemQuantity;
    private EditText itemUsage;
    private TextView errorPhone;
    private TextView errorQuantity;
    private TextView errorUsage;

    public fragment_item_reservation_details_two() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_item_reservation_details_two.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_item_reservation_details_two newInstance(String param1, String param2) {
        fragment_item_reservation_details_two fragment = new fragment_item_reservation_details_two();
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
        final View view = inflater.inflate(R.layout.fragment_item_reservation_details_two, container, false);
        assetDb = new DatabaseHelper(getActivity());
        session = new session_management(getActivity().getApplicationContext());

        // find View
        img = view.findViewById(R.id.image_item);
        itemName = view.findViewById(R.id.itemName2);
        departmentName = view.findViewById(R.id.itemDepartment2);
        description = view.findViewById(R.id.itemDesc2);


        phoneNo = view.findViewById(R.id.IR_edit_phone);
        spinnerDate = view.findViewById(R.id.spinner_item_Date);
        itemQuantity = view.findViewById(R.id.IR_edit_amount);
        itemUsage = view.findViewById(R.id.editTextUsage);

        errorPhone = view.findViewById(R.id.error_phone);
        errorQuantity = view.findViewById(R.id.error_amount);
        errorUsage = view.findViewById(R.id.error_usage);

        save = view.findViewById(R.id.button_save);
        cancel = view.findViewById(R.id.button_cancel);

        // Set item info
        Bundle bundle = this.getArguments();
        String item = "";

        if(bundle != null){
            item = bundle.getString("item");
        }


        if (item.equals("Table")) {

            img.setImageResource(R.drawable.table);
        } else if (item.equals("Chair")){
            img.setImageResource(R.drawable.chair);

        } else if (item.equals("Microphone")) {
            img.setImageResource(R.drawable.microphone);
        } else {
            img.setImageResource(R.drawable.speaker);
        }

        Cursor res = assetDb.fetchItemData(item);
        if (res.getCount() == 0) {

            Log.v("Error", "No value is found");
        } else {
            itemName.setText(res.getString(1));
            departmentName.setText(res.getString(2));
            description.setText(res.getString(4));
        }

        // Date
        final String[] selectedDate = new String[1];
        ArrayAdapter<String> myAdapter_date = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.dates));
        myAdapter_date.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(myAdapter_date);
        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate[0] = adapterView.getItemAtPosition(i).toString();
                Log.d(selectedDate[0],"test");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dateSelected = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Quantity
        final String finalItem = item;
        final boolean[] quantityTrue = {false};
        itemQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().equals("")) {

                    int amount = Integer.parseInt(s.toString());
                    Cursor cursor = assetDb.fetchItemQuantityData(finalItem, dateSelected);
                    if (cursor.getCount() == 0) {

                        Log.v("Error", "No value is found");
                    } else {
                        int quanLeft = cursor.getInt(1);
                        if (amount > quanLeft || quanLeft < 1) {
                            errorQuantity.setVisibility(View.VISIBLE);
                            errorQuantity.setText(getString(R.string.error_quantity_warning, quanLeft));
                            quantityTrue[0] = false;
                        } else {
                            errorQuantity.setVisibility(View.INVISIBLE);
                            quantityTrue[0] = true;
                        }
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // validation and save to database
        final String finalItem1 = item;
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Toast.makeText(fragment_item_reservation_details_two.this,
//                        getResources().getString(R.string.item_reserve_successful),
//                        Toast.LENGTH_SHORT).show();


                // Validate if empty
                if (TextUtils.isEmpty(phoneNo.getText()) || TextUtils.isEmpty(itemQuantity.getText()) || TextUtils.isEmpty(itemUsage.getText())) {

                    if (TextUtils.isEmpty(phoneNo.getText())) {
                        errorPhone.setVisibility(View.VISIBLE);
                        errorPhone.setText(R.string.required_phoneNum);
                    } else {
                        errorPhone.setVisibility(View.INVISIBLE);
                    }

                    if (TextUtils.isEmpty(itemQuantity.getText())) {
                        errorQuantity.setVisibility(View.VISIBLE);
                        errorQuantity.setText(R.string.required_quantity);
                    } else {
                        errorQuantity.setVisibility(View.INVISIBLE);
                    }

                    if (TextUtils.isEmpty(itemUsage.getText())) {
                        errorUsage.setVisibility(View.VISIBLE);
                        errorUsage.setText(R.string.required_usage);
                    } else {

                        errorUsage.setVisibility(View.INVISIBLE);
                    }

                    Toast.makeText(getContext(), "Please fill in all details.", Toast.LENGTH_SHORT).show();

                } else {

                    errorPhone.setVisibility(View.INVISIBLE);
                    errorUsage.setVisibility(View.INVISIBLE);

                    // Validate if the quantity is in acceptable range
                    if (quantityTrue[0]) {

                        // Save into database
                        assetDb.insertItemBorrowData(dateSelected, Integer.parseInt(String.valueOf(itemQuantity.getText())),
                                "Borrowed", String.valueOf(phoneNo.getText()), String.valueOf(itemUsage.getText()), finalItem1) ;

                        // Go to reservation status
                        fragment = new fragment_status();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_container, fragment);
                        ft.commit();

                        Toast.makeText(getContext(), "The reservation is successful.", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), "Please fill in the quantity within the available range.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        // go back to item reservation fragment
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new fragment_item_reservation();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }
        });

        return view;
    }
}