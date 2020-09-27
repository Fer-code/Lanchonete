package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Produto extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_VALOR = "com.example.restau.VALUE";
    public final static String EXTRA_MESSAGE = "com.example.restau.MENSAGEM";
    public final static String EXTRA_MESSAGE_PAGAMENTO = "com.example.restau.PAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        getSupportActionBar().hide();
    }

    public void pedido(View p){

        CheckBox ckCoxinha = findViewById(R.id.ckCoxinha);
        CheckBox ckEmpada = findViewById(R.id.ckEmpada);
        CheckBox ckPaoQ = findViewById(R.id.ckPaoQueijo);
        CheckBox ckEsfira = findViewById(R.id.ckEsfiraCarne);

        CheckBox ckBrigadeiro = findViewById(R.id.ckBrigadeiro);
        CheckBox ckCocaCola = findViewById(R.id.ckCoca);
        CheckBox ckGuarana = findViewById(R.id.ckGuarana);
        CheckBox ckAgua = findViewById(R.id.ckAgua);

        RadioButton rdCartao = findViewById(R.id.rdCartao);
        RadioButton rdDinheiro = findViewById(R.id.rdDinheiro);


        String pagamento = "";
        String gostos = "";
        double valor = 0;
        if(ckCoxinha.isChecked()) {
            gostos = "Coxinha\n";
            valor += 4;
        }
        if (ckEmpada.isChecked()){
            gostos += "Empada\n";
            valor += 3;
        }
        if (ckPaoQ.isChecked()){
            gostos += "Pão de queijo\n";
            valor += 3.5;
        }
        if (ckEsfira.isChecked()){
            gostos += "Esfira de Carne\n";
            valor += 6;
        }

        if (ckBrigadeiro.isChecked()){
            gostos += "Brigadeiro\n";
            valor += 2;
        }
        if (ckCocaCola.isChecked()){
            gostos += "Coca-Cola\n";
            valor += 5;
        }
        if (ckGuarana.isChecked()){
            gostos += "Guaraná\n";
            valor += 5;
        }
        if (ckAgua.isChecked()){
            gostos += "Água\n";
            valor += 4.5;
        }



        if(rdCartao.isChecked()){
            pagamento = "Cartão";
        }
        if(rdDinheiro.isChecked()){
            pagamento = "Dinheiro";
            valor = valor - (valor * 1/10);
        }

        String total = String.valueOf(valor);
        Intent intent = new Intent(this, Pagamento.class);
        intent.putExtra(EXTRA_MESSAGE, gostos);
        intent.putExtra(EXTRA_MESSAGE_VALOR, total);
        intent.putExtra(EXTRA_MESSAGE_PAGAMENTO, pagamento);
        startActivity(intent);


    }

}