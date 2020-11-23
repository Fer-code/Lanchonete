package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
    }

    public void perfil(View b){
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }

    public void produtos(View p){
        Intent intent = new Intent(this, Produto.class);
        startActivity(intent);
    }
}