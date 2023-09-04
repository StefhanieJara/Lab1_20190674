package com.example.lab1_jaramillo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Estadisticas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        // Obtén la lista de tiempos enviada desde JuegoActivity2
        Intent intent = getIntent();
        long[] tiempos = intent.getLongArrayExtra("tiempos");

        // Aquí puedes mostrar los tiempos como desees, por ejemplo, en un TextView
        TextView textView = findViewById(R.id.impresiones);

        StringBuilder stringBuilder = new StringBuilder("Tiempos de juegos:\n");

        for (int i = 0; i < tiempos.length; i++) {
            stringBuilder.append("Juego ").append(i + 1).append(": tiempo = ").append(tiempos[i]).append(" ms\n");
        }

        textView.setText(stringBuilder.toString());
    }
}