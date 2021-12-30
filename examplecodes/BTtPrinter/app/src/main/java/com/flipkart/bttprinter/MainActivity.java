package com.flipkart.bttprinter;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.mazenrashed.printooth.Printooth;
import com.mazenrashed.printooth.data.printable.ImagePrintable;
import com.mazenrashed.printooth.data.printable.Printable;
import com.mazenrashed.printooth.data.printable.RawPrintable;
import com.mazenrashed.printooth.data.printable.TextPrintable;
import com.mazenrashed.printooth.data.printer.DefaultPrinter;
import com.mazenrashed.printooth.ui.ScanningActivity;
import com.mazenrashed.printooth.utilities.Printing;
import com.mazenrashed.printooth.utilities.PrintingCallback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PrintingCallback {

    Printing printing;
    Button btnPrintHello,btnPrintImage,btnPairUnpair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        btnPrintHello = (Button) findViewById(R.id.printHello);
        btnPrintImage = (Button) findViewById(R.id.printImage);
        btnPairUnpair = (Button) findViewById(R.id.pairUnpair);

        if(printing!=null){
            printing.setPrintingCallback(this);
        }

        btnPairUnpair.setOnClickListener(v -> {
            if(Printooth.INSTANCE.hasPairedPrinter()){
                Printooth.INSTANCE.removeCurrentPrinter();
            }else{
                startActivityForResult(new Intent(MainActivity.this, ScanningActivity.class), ScanningActivity.SCANNING_FOR_PRINTER);
                changePairAndUnpair();
            }
        });

        btnPrintImage.setOnClickListener(v -> {
         if(!Printooth.INSTANCE.hasPairedPrinter()){
             startActivityForResult(new Intent(MainActivity.this, ScanningActivity.class), ScanningActivity.SCANNING_FOR_PRINTER);
             changePairAndUnpair();
         }else{
             printImage();
         }
        });
        
        btnPrintHello.setOnClickListener(v -> {
            if(!Printooth.INSTANCE.hasPairedPrinter()){
                startActivityForResult(new Intent(MainActivity.this, ScanningActivity.class), ScanningActivity.SCANNING_FOR_PRINTER);
                changePairAndUnpair();
            }else{
                printHello();
            }
        });

        changePairAndUnpair();
    }

    private void printHello() {
        ArrayList<Printable> printable = new ArrayList<>();
        printable.add(new RawPrintable.Builder(new byte[]{27,100,4}).build());

        printable.add(new TextPrintable.Builder().setText("Hello word").setCharacterCode(DefaultPrinter.Companion.getCHARCODE_PC1252()).setNewLinesAfter(1).build());

        printing.print(printable);
    }

    private void printImage() {
        ArrayList<Printable> printable = new ArrayList<>();

        Picasso.get()
                .load("https://image.similarpng.com/very-thumbnail/2020/11/Black-Android-icon-design-on-transparent-background-PNG.png")
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        printable.add(new ImagePrintable.Builder(bitmap).build());
                        printing.print(printable);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    private void changePairAndUnpair() {
        if(Printooth.INSTANCE.hasPairedPrinter()){
            btnPairUnpair.setText(new StringBuilder("Unpair ").append(Printooth.INSTANCE.getPairedPrinter().getName().toString()));
        }else{
            btnPairUnpair.setText("Pair with printer");
        }
    }

    @Override
    public void connectingWithPrinter() {
        Toast.makeText(this, "Connecting to printer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectionFailed(String s) {
        Toast.makeText(this, "Failed: "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String s) {
        Toast.makeText(this, "Error: "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessage(String s) {
        Toast.makeText(this, "Message: "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void printingOrderSentSuccessfully() {
        Toast.makeText(this, "Printing Order sent to printer", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK){
            initPrinting();
            changePairAndUnpair();
        }
    }

    private void initPrinting() {
        if(Printooth.INSTANCE.hasPairedPrinter()){
            printing = Printooth.INSTANCE.printer();
        }

        if(printing!=null){
            printing.setPrintingCallback(this);
        }
    }
}