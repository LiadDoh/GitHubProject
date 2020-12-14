package com.example.hw1.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.hw1.Interfaces.CallBackTop;
import com.example.hw1.R;
import com.example.hw1.objects.Player;
import com.example.hw1.objects.TopTen;
import com.google.gson.Gson;

import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;
import static com.example.hw1.helpers.Constants.SP_FILE;
import static com.example.hw1.helpers.Constants.TOP_TEN;

public class Fragment_List extends Fragment {

    private CallBackTop callBackTop;
    private ListView list_view;

    public void setCallBack_top(CallBackTop callBackTop) {
        this.callBackTop = callBackTop;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        list_view = (ListView) view.findViewById(R.id.list_view);
        SharedPreferences prefs = getContext().getSharedPreferences(SP_FILE, MODE_PRIVATE);
        Gson gson = new Gson();
        TopTen topTenList = generateData(prefs, gson);
        if (topTenList == null)
            topTenList = new TopTen();
        ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, topTenList.getRecords());
        topTenList.players.sort(Collections.reverseOrder());
        list_view.setAdapter(arrayAdapter);
        initViews();
        return view;
    }

    private void initViews() {
        list_view.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            if (callBackTop != null) {
                Player p = (Player) list_view.getItemAtPosition(position);
                callBackTop.showLocation(p.getLatitude(), p.getLongitude());
            }
        });
    }

    private TopTen generateData(SharedPreferences prefs, Gson gson) {
        String json = prefs.getString(TOP_TEN, "");
        return gson.fromJson(json, TopTen.class);

    }

}