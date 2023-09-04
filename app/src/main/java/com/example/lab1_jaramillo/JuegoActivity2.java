package com.example.lab1_jaramillo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoActivity2 extends AppCompatActivity {

    private String[] palabras;
    private String palabraActual;
    private TextView[] vistatextos;
    private Random random;

    private LinearLayout palabrasLayout;
    private AdaptadordeLetras adaptador;
    private GridView gridView;
    private int numCorr;
    private int numChar;
    private ImageView[]parts;
    private int sizeParts=6;
    private int currPart;

    private long startTime;

    private List<Long> duracionesDeJuegos = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego2);
        palabras = getResources().getStringArray(R.array.palabras); //obtenemos el arreglo de palabras
        palabrasLayout=findViewById(R.id.palabras);
        gridView=findViewById(R.id.letras);
        random= new Random();
        parts= new ImageView[sizeParts];
        parts[0] =findViewById(R.id.imageView2);
        parts[1] =findViewById(R.id.imageView4);
        parts[2] =findViewById(R.id.imageView8);
        parts[3] =findViewById(R.id.imageView3);
        parts[4] =findViewById(R.id.imageView5);
        parts[5] =findViewById(R.id.imageView9);
        jugando();



    }

    private void jugando(){
        startTime = System.currentTimeMillis(); //agregamos

        String palabranueva= palabras[random.nextInt(palabras.length)];

        while(palabranueva.equals(palabraActual)) palabranueva=palabras[random.nextInt(palabras.length)];
        palabraActual=palabranueva;

        vistatextos= new TextView[palabraActual.length()];

        palabrasLayout.removeAllViews();
        for(int i=0; i<palabraActual.length();i++){
            vistatextos[i]=new TextView(this);
            char caracter = palabraActual.charAt(i);
            String texto = "" + caracter;
            vistatextos[i].setText(texto);
            vistatextos[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            vistatextos[i].setGravity(Gravity.CENTER);
            vistatextos[i].setTextColor(Color.WHITE);
            vistatextos[i].setBackgroundResource(R.drawable.letter_bg);
            palabrasLayout.addView(vistatextos[i]); //para que se vaya viendo las letras

        }
        adaptador= new AdaptadordeLetras(this);
        gridView.setAdapter(adaptador);
        numCorr=0;
        currPart=0;
        numChar=palabraActual.length();

        for(int i=0; i<sizeParts;i++){
            parts[i].setVisibility(View.INVISIBLE);
        }

    }

    public void letterPressed(View view){
        String letter = ((TextView) view).getText().toString();
        char letterChar =letter.charAt(0);

        view.setEnabled(false);

        boolean correct=false;

        for(int i=0;i<palabraActual.length();i++){
            if(palabraActual.charAt(i)==letterChar){
                correct=true;
                numCorr++;
                vistatextos[i].setTextColor(Color.BLACK);
            }

        }
        if(correct){
            if(numCorr==numChar){
                disableButtons();

                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Felicidades Ganaste");
                builder.setMessage("Felicidades \n\n La respuesta era \n\n"+palabraActual);
                builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        JuegoActivity2.this.jugando();
                    }
                });

                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        JuegoActivity2.this.finish();
                    }
                });
                builder.show();

                long endTime = System.currentTimeMillis(); // Fin del juego
                long duracion = endTime - startTime;
                duracionesDeJuegos.add(duracion);

                Intent estadisticasIntent = new Intent(this, Estadisticas.class);
                estadisticasIntent.putExtra("tiempos", convertirLista(duracionesDeJuegos));

            }

        } else if (currPart<sizeParts) {
            parts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        }else{
            disableButtons();
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("Perdiste");
            builder.setMessage("Lo siento \n\n La respuesta era \n\n"+palabraActual);
            builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    JuegoActivity2.this.jugando();
                }
            });

            builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    JuegoActivity2.this.finish();
                }
            });
            builder.show();

            long endTime = System.currentTimeMillis(); // Fin del juego
            long duracion = endTime - startTime;
            duracionesDeJuegos.add(duracion);
            Intent estadisticasIntent = new Intent(this, Estadisticas.class);
            estadisticasIntent.putExtra("tiempos", convertirLista(duracionesDeJuegos));

        }





    }
    public void disableButtons(){
        for(int i=0; i<gridView.getChildCount();i++){
            gridView.getChildAt(i).setEnabled(false);


        }
    }

    // Método para convertir la lista a un array de long

    public void estadistica(View view){
        Intent estadisticasIntent = new Intent(JuegoActivity2.this, Estadisticas.class);
        estadisticasIntent.putExtra("tiempos", convertirLista(duracionesDeJuegos));
        startActivity(estadisticasIntent);

    }

    // Método para convertir la lista a un array de long
    public long[] convertirLista(List<Long> lista) {
        long[] array = new long[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            array[i] = lista.get(i);
        }
        return array;
    }



}