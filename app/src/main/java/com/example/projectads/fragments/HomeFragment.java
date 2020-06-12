package com.example.projectads.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectads.AddGroceryActivity;
import com.example.projectads.Model.Grocery;
import com.example.projectads.fragments.GroceryAdapter;
import com.example.projectads.ViewModel.GroceryViewModel;
import com.example.projectads.MainActivity;
import com.example.projectads.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    View view;
    public static final int ADD_GROCERY_REQUEST = 1;
    GroceryViewModel groceryViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final GroceryAdapter adapter = new GroceryAdapter();
        recyclerView.setAdapter(adapter);

        groceryViewModel = new ViewModelProvider(this).get(GroceryViewModel.class);
        groceryViewModel.getAllGroceries().observe(getActivity(), new Observer<List<Grocery>>() {
            @Override
            public void onChanged(List<Grocery> groceries) {
                adapter.setGroceries(groceries);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                groceryViewModel.delete(adapter.getGroceryAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Grocery Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        ///////////////////////////////////////////////ADD FLOATING BUTToN///////////////////////////////////////////////////////
        FloatingActionButton buttonAddGrocery = view.findViewById(R.id.button_add_grocery);
        buttonAddGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddGroceryActivity.class);
                startActivityForResult(intent, ADD_GROCERY_REQUEST);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_GROCERY_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddGroceryActivity.EXTRA_NAME);
            int quantity = data.getIntExtra(AddGroceryActivity.EXTRA_QUANTITY, 1);

            Grocery grocery = new Grocery(name, quantity);
            groceryViewModel.insert(grocery);

            Toast.makeText(getContext(), "Grocery Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Grocery not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
