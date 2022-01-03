package com.flipkart.shareadapterandinterface;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class YourAdapter extends RecyclerView.Adapter<YourAdapter.MyViewHolder> {
    List<String> dataList;
    testInterface testInterface;
    public YourAdapter(List<String> data,testInterface testInterface) {
        this.dataList=data;
        this.testInterface=testInterface;
    }

    @NonNull
    @Override
    public YourAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        YourAdapter.MyViewHolder holder = new YourAdapter.MyViewHolder(view,testInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text_name.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("yag", String.valueOf(dataList.size()));
        return dataList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text_name;
        testInterface testInterface;
        public MyViewHolder(View itemView,testInterface testInterface) {
            super(itemView);
            text_name = (TextView)itemView.findViewById(R.id.itemName);
            this.testInterface=testInterface;
            text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    testInterface.testFunction(String.valueOf(text_name.getText()));
                }
            });
        }
    }
}
