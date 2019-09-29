package com.example.mdbpurchases2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_adapter, parent, false);
        return new CustomViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter.CustomViewHolder holder, int position) {
        Purchase purchase = filteredPurchase.get(position);
        holder.cost.setText(purchase.getCost());
        holder.date.setText(purchase.getDate().toString());
        holder.description.setText(purchase.getDescription());
        holder.supplier.setText(purchase.getSupplier());


    }

    @Override
    public int getItemCount() {
        return filteredPurchase.size();
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
        TextView cost;
        TextView date;
        TextView description;
        TextView supplier;


        public CustomViewHolder(View itemView) {
            super(itemView);
            this.cost = itemView.findViewById(R.id.cost);
            this.date = itemView.findViewById(R.id.date);
            this.description = itemView.findViewById(R.id.description);
            this.supplier = itemView.findViewById(R.id.supplier);

        }

        public void bind(Purchase purchase) {

        }
    }
}