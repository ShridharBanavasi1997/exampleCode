package com.flipkart.shareadapterandinterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements testInterface{
    private List<String> listData;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        listData = new ArrayList<>();
        listData.add("Fragment example 1");
        listData.add("Fragment example 2");
        listData.add("Fragment example 3");
        RecyclerView rec = view.findViewById(R.id.recy);
        rec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        YourAdapter adapter = new YourAdapter(listData,this);
        rec.setAdapter(adapter);


        return view;
    }

    @Override
    public void testFunction(String data) {
        Toast.makeText(getContext(), "data: "+data, Toast.LENGTH_SHORT).show();
        Log.d("fragment","data"+data);
    }
}