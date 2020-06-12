package com.example.projectads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryHolder> {

    private List<Grocery> groceries = new ArrayList<>();


    @NonNull
    @Override
    public GroceryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_item, parent, false);
        return new GroceryHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull GroceryHolder holder, int position) {
        Grocery currentGrocery = groceries.get(position);
        holder.textViewName.setText(currentGrocery.getName());
        holder.textViewQuantity.setText(String.valueOf(currentGrocery.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return groceries.size();
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;
        notifyDataSetChanged();
    }

    public Grocery getGroceryAt(int position) {
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
