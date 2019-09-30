package com.example.mdbpurchases2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_cards);
        btnAdd = findViewById(R.id.btnAdd);
        Purchase newPurchase = new Purchase("70.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());

        // used to write to the database, when doing setValue, the the child method call
        // should be passing a unique ID each time we want to create a new purchase object
        // currently, this code is writes over the purchase that is shown for purchase2
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        getWindow().getDecorView().setBackgroundColor(Color.RED);
        Purchase A1 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        Purchase A2 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        Purchase A3 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        Purchase A4 = new Purchase("$69.00", "Cheese", "Walmart", "https://imgur.com/a/2sKXERB", System.currentTimeMillis());
        purchases = new ArrayList<>();
        // used to read the data -- for some reason the purchases list is getting the data but its
        // being displayed in the recyclerview
        Query ref = mDatabase.child("purchases").orderByChild("timestamp");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                purchases.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    Purchase purchase = ds.getValue(Purchase.class);
                    purchases.add(purchase);
                }
                // have to do the recyclerview stuff here because its an async task
                // that needs to be completed before the adapter can be created

                final RecyclerView recList = (RecyclerView) findViewById(R.id.recyclerView);
                recList.setHasFixedSize(true);
                LinearLayoutManager llm = new LinearLayoutManager(PurchaseCards.this);
                llm.setReverseLayout(true);
                llm.setStackFromEnd(true);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recList.setLayoutManager(llm);
                adapter = new PurchaseAdapter(getApplicationContext(), purchases);
                recList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d("XYZ", ((Integer) purchases.size()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("XYZ", "error");
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PurchaseCards.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
}
