package com.swe401.getasset;

import android.content.ClipData;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    private ListView listItem;
    List <DatabaseHelper.ItemBorrow> itemList;

    Integer count;
    String[] itemDate;
    String[] itemName;
    Integer[] itemQuantity;
    String[] itemStatus;



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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_item_status, container, false);
        listItem = view.findViewById(R.id.listView);

        assetDb = new DatabaseHelper(getActivity());

        CustomAdapter customAdapter = new CustomAdapter();
        listItem.setAdapter(customAdapter);

        return view;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return assetDb.getCountItemBorrowData("LEE ROU");
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

//            ImageView imgItem = (ImageView) view.findViewById(R.id.image_item);
            TextView itemStatusDate = (TextView) view.findViewById(R.id.itemStatusDate);
            TextView itemStatusName = (TextView) view.findViewById(R.id.itemStatusName);
            TextView itemStatusQuantity = (TextView) view.findViewById(R.id.itemStatusQuantity);
            TextView itemStatusStatus = (TextView) view.findViewById(R.id.itemStatusStatus);

            count = assetDb.getCountItemBorrowData("LEE ROU");

            String[] itemImage = new String[count];
            String[] itemDate = new String[count];
            String[] itemName = new String[count];
            Integer[] itemQuantity = new Integer[count];
            String[] itemStatus = new String[count];


            if (count > 0) {

                int i = 0;

                itemList = assetDb.fetchItemList("LEE ROU");

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