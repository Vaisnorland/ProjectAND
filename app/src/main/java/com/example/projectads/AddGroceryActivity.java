package com.example.projectads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddGroceryActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.example.projectads.EXTRA_NAME";
    public static final String EXTRA_QUANTITY =
            "com.example.projectads.EXTRA_QUANTITY";

    private EditText editTextName;
    private NumberPicker numberPickerQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);

        LinearLayout linearLayout = findViewById(R.id.add_grocery_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        editTextName = findViewById(R.id.edit_text_name);
        numberPickerQuantity = findViewById(R.id.number_picker_quantity);

        numberPickerQuantity.setMinValue(1);
        numberPickerQuantity.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Grocery");
    }

    private void saveGrocery() {
        String name = editTextName.getText().toString();
        int quantity = numberPickerQuantity.getValue();

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a name of grocery", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_QUANTITY, quantity);


        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_grocery_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_grocery:
                saveGrocery();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
