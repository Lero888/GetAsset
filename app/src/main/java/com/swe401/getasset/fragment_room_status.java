package com.swe401.getasset;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_room_status#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_room_status extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseHelper assetDb;
    session_management session;
    Integer count;

    ListView listItem;
    List<DatabaseHelper.RoomBorrow> roomList;
    public fragment_room_status() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_room_status.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_room_status newInstance(String param1, String param2) {
        fragment_room_status fragment = new fragment_room_status();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_room_status, container, false);
        assetDb = new DatabaseHelper(getActivity());
        HashMap<String, String> user = session.getUserDetails();
        final String username = user.get(session_management.KEY_NAME);

        listItem = view.findViewById(R.id.room_list);
        final fragment_room_status.CustomAdapter customAdapter = new fragment_room_status.CustomAdapter();
        customAdapter.notifyDataSetChanged();
        listItem.setAdapter(customAdapter);

        return view;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            HashMap<String, String> user = session.getUserDetails();
            String username = user.get(session_management.KEY_NAME);
            return assetDb.getCountRoomBorrowData(username);
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
            View view = getLayoutInflater().inflate(R.layout.custom_layout_room_list, null);

            HashMap<String, String> user = session.getUserDetails();
            String username = user.get(session_management.KEY_NAME);

            TextView roomNo = (TextView) view.findViewById(R.id.room_No);
            TextView roomDate = (TextView) view.findViewById(R.id.room_Date);
            TextView roomTime = (TextView) view.findViewById(R.id.room_Time);

            count = assetDb.getCountRoomBorrowData(username);


            String[] RoomNo = new String[count];
            String[] RoomDate = new String[count];
            String[] RoomTime = new String[count];


            if (count > 0) {

                int i = 0;

                roomList = assetDb.fetchRoomList(username);

                for (DatabaseHelper.RoomBorrow item: roomList) {


                    RoomNo[i] = item.getRoomNo();
                    RoomDate[i] = item.getRoomDate();
                    RoomTime[i] = item.getRoomTime();
                    i += 1;

                }


            }

            if (count > 0) {
                roomNo.setText(RoomNo[position]);
                roomDate.setText(RoomDate[position]);
                roomTime.setText(RoomTime[position]);

            }


            return view;
        }
    }
}