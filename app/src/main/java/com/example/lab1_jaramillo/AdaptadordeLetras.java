package com.example.lab1_jaramillo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class AdaptadordeLetras extends BaseAdapter {
    private String[] letras;
    private LayoutInflater letrasInf;

    public AdaptadordeLetras(Context context){
        letras = new String[26];
        for(int i=0; i<letras.length;i++){
            letras[i]=""+(char)(i+'A');

        }
        letrasInf=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {

        return letras.length;
    }

    @Override
    public Object getItem(int i) {

        return null;
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btnLetter;
        if(view==null){
            btnLetter=(Button) letrasInf.inflate(R.layout.letras,viewGroup,false);
        }else {
            btnLetter=(Button) view;
        }
        btnLetter.setText(letras[i]);

        return btnLetter;
    }
}
