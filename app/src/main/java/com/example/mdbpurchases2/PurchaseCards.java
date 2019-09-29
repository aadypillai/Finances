package com.example.mdbpurchases2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PurchaseCards extends AppCompatActivity {

    PurchaseAdapter adapter;
    ArrayList<Purchase> purchases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_cards);
        Purchase newPurchase = new Purchase("70.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("purchases").child("purchase2").setValue(newPurchase);
//        getWindow().getDecorView().setBackgroundColor(Color.RED);
        Purchase A1 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        Purchase A2 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        Purchase A3 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        Purchase A4 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        purchases = new ArrayList<>();
        purchases.add(A1);
        purchases.add(A2);
        purchases.add(A3);
        purchases.add(A4);
        final RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        adapter = new PurchaseAdapter(getApplicationContext(), purchases);
        recList.setAdapter(adapter);

        DatabaseReference ref = mDatabase.child("purchases");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    Purchase purchase = ds.getValue(Purchase.class);
                    purchases.add(purchase);
                    adapter.notifyDataSetChanged();
                    Log.d("XYZ", ((Integer) purchases.size()).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("XYZ", "error");

            }
        });

        Log.d("henlo" , "got past");
    }
}
