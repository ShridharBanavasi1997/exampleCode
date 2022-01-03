package com.flipkart.shareadapterandinterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements testInterface{
private List<String> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listData = new ArrayList<>();
        listData.add("example 1");
        listData.add("example 2");
        listData.add("example 3");
        RecyclerView rec = findViewById(R.id.recy);
        rec.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        YourAdapter adapter = new YourAdapter(listData,this);
        rec.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void testFunction(String data) {
        Toast.makeText(this, "data: "+data, Toast.LENGTH_SHORT).show();
        Log.d("fragment","data"+data);
    }
}