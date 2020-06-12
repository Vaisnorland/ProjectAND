package com.example.projectads.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectads.Model.Grocery;
import com.example.projectads.R;

import java.util.ArrayList;
import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryHolder> {

    private List<com.example.projectads.Model.Grocery> groceries = new ArrayList<>();


    @NonNull
    @Override
    public GroceryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_item, parent, false);
        return new GroceryHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull GroceryHolder holder, int position) {
        com.example.projectads.Model.Grocery currentGrocery = groceries.get(position);
        holder.textViewName.setText(currentGrocery.getName());
        holder.textViewQuantity.setText(String.valueOf(currentGrocery.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return groceries.size();
    }

    public void setGroceries(List<com.example.projectads.Model.Grocery> groceries) {
        this.groceries = groceries;
        notifyDataSetChanged();
    }

    public com.example.projectads.Model.Grocery getGroceryAt(int position) {
        return  groceries.get(position);
    }

    class GroceryHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewQuantity;

        public GroceryHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewQuantity = itemView.findViewById(R.id.text_view_quantity);

        }
    }
}
