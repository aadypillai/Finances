package com.example.mdbpurchases2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.CustomViewHolder> implements Filterable {
    Context context;
    private ArrayList<Purchase> allPurchase;
    private ArrayList<Purchase> filteredPurchase;

    public PurchaseAdapter(Context context, ArrayList<Purchase> purchaseList) {
        this.context = context;
        allPurchase = new ArrayList<>();
        allPurchase.addAll(purchaseList);
        filteredPurchase = new ArrayList<>();
        filteredPurchase.addAll(purchaseList);
    }

    @NonNull
    @Override
    public PurchaseAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter.CustomViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void addPurchase(Purchase purchase) {
        allPurchase.add(purchase);
        notifyDataSetChanged();
    }

    public void deletePurchase(Purchase purchase) {
        allPurchase.remove(purchase);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    private Filter purchaseFilter = new Filter() {
        ArrayList<Purchase> filtered;

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(allPurchase);
            } else {
                String filterString = charSequence.toString().toLowerCase().trim();
                for (Purchase purchase: allPurchase) {
                    if (purchase.getDescription().toLowerCase().contains(filterString)) {
                        filtered.add(purchase);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredPurchase.clear();
            filteredPurchase.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class CustomViewHolder extends RecyclerView.ViewHolder {


        public CustomViewHolder(View itemView) {
            super(itemView);

        }

        public void bind(Purchase purchase) {

        }
    }
}