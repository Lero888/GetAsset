package com.swe401.getasset;

import android.app.Dialog;
import android.content.ClipData;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_item_status#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_item_status<yes> extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHelper assetDb;
    session_management session;

    private ListView listItem;
    List <DatabaseHelper.ItemBorrow> itemList;
    Button buttonPopup;
    ImageButton close;
    Dialog myDialog;
    EditText numberPassword;
    Button buttonSubmitPassword;
    TextView errorPassword;
    String item;
    String date;
    Integer count;


    public fragment_item_status() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_item_status.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_item_status newInstance(String param1, String param2) {
        fragment_item_status fragment = new fragment_item_status();
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

        session = new session_management(getActivity().getApplicationContext());
        session.checkLogin();
        myDialog = new Dialog(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_item_status, container, false);
        assetDb = new DatabaseHelper(getActivity());
        HashMap<String, String> user = session.getUserDetails();
        final String username = user.get(session_management.KEY_NAME);

        listItem = view.findViewById(R.id.listView);
        final CustomAdapter customAdapter = new CustomAdapter();
        customAdapter.notifyDataSetChanged();
        listItem.setAdapter(customAdapter);

        // pop up
        myDialog.setContentView(R.layout.custom_popup);
        numberPassword = myDialog.findViewById(R.id.editTextNumberPassword);
        buttonSubmitPassword = myDialog.findViewById(R.id.submitPassword);
        errorPassword = myDialog.findViewById(R.id.error_pw);

        // when a specific item is clicked
        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView status  = view.findViewById(R.id.itemStatusStatus);
                String statusItem = status.getText().toString();

                if (statusItem.equals("Borrowed") || statusItem.equals("Retrieved")) {


                    errorPassword.setVisibility(View.INVISIBLE);
                    numberPassword.getText().clear();
                    TextView viewItemName = view.findViewById(R.id.itemStatusName);
                    TextView dateBorrow = view.findViewById(R.id.itemStatusDate);

                    item = viewItemName.getText().toString();
                    date = dateBorrow.getText().toString();
                    showPopUp(item, statusItem);


                }


            }
        });

        buttonSubmitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pw = numberPassword.getText().toString();
                boolean checkPassword = assetDb.validatePassword(pw, item);

                if (checkPassword) {

                    assetDb.updateItemBorrowStatus(username, date, item);
                    myDialog.dismiss();

                } else {

                    errorPassword.setVisibility(View.VISIBLE);
                    if (pw.length()==0){
                        errorPassword.setText("FIELD CANNOT BE EMPTY");
                    } else {
                        errorPassword.setText("WRONG PASSWORD");
                    }
                }
            }

        });


        return view;
    }

    // Pop up for retrieving/returning items
    public void showPopUp(String item, String status) {

        close = myDialog.findViewById(R.id.closePopup);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }

        });

        myDialog.show();

        TextView instruction = myDialog.findViewById(R.id.popUpInstruction);

        if (status.equals("Borrowed")) {
            instruction.setText("You are retrieving the items. Please request the staff to input the password.");
        } else if (status.equals("Retrieved")) {
            instruction.setText("You are returning the items. Please request the staff to input the password.");
        }
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            HashMap<String, String> user = session.getUserDetails();
            String username = user.get(session_management.KEY_NAME);
            return assetDb.getCountItemBorrowData(username);
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.custom_layout_list, null);

            HashMap<String, String> user = session.getUserDetails();
            String username = user.get(session_management.KEY_NAME);

//            ImageView imgItem = (ImageView) view.findViewById(R.id.image_item);
            TextView itemStatusDate = (TextView) view.findViewById(R.id.itemStatusDate);
            TextView itemStatusName = (TextView) view.findViewById(R.id.itemStatusName);
            TextView itemStatusQuantity = (TextView) view.findViewById(R.id.itemStatusQuantity);
            TextView itemStatusStatus = (TextView) view.findViewById(R.id.itemStatusStatus);
//            buttonPopup = view.findViewById(R.id.buttonPassword);

    //            buttonPopup.setOnClickListener(new View.OnClickListener() {
    //                @Override
    //                public void onClick(View view) {
    //
    //                    showPopUp(view);
    //                }
    //            });

            count = assetDb.getCountItemBorrowData(username);

            String[] itemImage = new String[count];
            String[] itemDate = new String[count];
            String[] itemName = new String[count];
            Integer[] itemQuantity = new Integer[count];
            String[] itemStatus = new String[count];


            if (count > 0) {

                int i = 0;

                itemList = assetDb.fetchItemList(username);

                for (DatabaseHelper.ItemBorrow item: itemList) {

//                    itemImage[i] = item.getImage();
                    itemDate[i] = item.getDate();
                    itemName[i] = item.getName();
                    itemQuantity[i] = item.getQuantity();
                    itemStatus[i] = item.getStatus();
                    i += 1;

                }


            }

            if (count > 0) {
                itemStatusDate.setText(itemDate[position]);
                itemStatusName.setText(itemName[position]);
                itemStatusQuantity.setText(String.valueOf(itemQuantity[position]));
                itemStatusStatus.setText(itemStatus[position]);

            }


            return view;
        }
    }
}