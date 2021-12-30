package com.flipkart.customdialgexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   BottomSheetDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn =findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });
    }

    private void Dialog() {
        List<String> sortByModelList = new ArrayList<>();
        sortByModelList.add("Data 1");
        sortByModelList.add("Data 2");
        sortByModelList.add("Data 3");
        sortByModelList.add("Data 4");
        dialog= new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dilog);
        Window window = getWindow();
        Rect displayRectangle = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        dialog.getWindow().setLayout((int) displayRectangle.width(), displayRectangle.height());

        RecyclerView sort_recycler_view = (RecyclerView)dialog.findViewById(R.id.sort_recycler_view);
        ImageView dialog_back_image = (ImageView)dialog.findViewById(R.id.dialog_back_image);

        sort_recycler_view.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        SortByAdapter sortByAdapter = new SortByAdapter(MainActivity.this,sortByModelList);
        sort_recycler_view.setAdapter(sortByAdapter);
        dialog_back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}