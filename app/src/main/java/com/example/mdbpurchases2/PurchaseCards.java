package com.example.mdbpurchases2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseCards extends AppCompatActivity {

    PurchaseAdapter adapter;
    ArrayList<String> purchases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_cards);
        getWindow().getDecorView().setBackgroundColor(Color.RED);
        Date date1 = new Date(696969);
        Purchase A1 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", date1);
        Purchase A2 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", date1);
        Purchase A3 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", date1);
        Purchase A4 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", date1);
        ArrayList<Purchase> pokemon = new ArrayList<>();
        pokemon.add(A1);
        pokemon.add(A2);
        pokemon.add(A3);
        pokemon.add(A4);
        final RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        adapter = new PurchaseAdapter(getApplicationContext(), pokemon);
        recList.setAdapter(adapter);
        Log.d("henlo" , "got past");
    }
}
