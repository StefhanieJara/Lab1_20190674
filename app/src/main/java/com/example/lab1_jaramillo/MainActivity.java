package com.example.lab1_jaramillo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView2 = findViewById(R.id.textView2);
        registerForContextMenu(textView2);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.textView2) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); // Obtener el ID del elemento seleccionado

        if (id == R.id.opcionroja) {
            textView2.setTextColor(Color.RED);
            return true;
        } else if (id == R.id.opcionazul) {
            textView2.setTextColor(Color.BLUE);
            return true;
        }else if (id == R.id.opcionverde) {
            textView2.setTextColor(Color.GREEN);
            return true;
        }

        return super.onContextItemSelected(item);

    }

    public void play(View view){
        Intent intent1= new Intent(this,JuegoActivity2.class);
        startActivity(intent1);

    }



}