package com.example.lanchonete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Vis_Cadastro extends AppCompatActivity {


    private TextView visNome;
    private TextView visEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis__cadastro);

        getSupportActionBar().hide();

        try {
            Bundle bundle = getIntent().getExtras();

            visNome = (TextView) findViewById(R.id.txtVisNome);
            visEmail = (TextView) findViewById(R.id.txtVisEmail);

            if (bundle != null){
                try {
                    byte[] imageInByte = bundle.getByteArray("imagem");
                    Bitmap bmp = BitmapFactory.decodeByteArray(imageInByte,0,imageInByte.length);
                    ImageView imgs = findViewById(R.id.visFoto);
                    imgs.setImageBitmap(bmp);

                    String email = bundle.getString(MainActivity.EXTRA_MESSAGE_EMAIL);
                    String nome = bundle.getString(MainActivity.EXTRA_MESSAGE_NOME);

                    String txtNome = String.format("%s", nome);
                    String txt = String.format(" %s ",email);

                    visNome.setText(txtNome);
                    visEmail.setText(txt );

                }catch (Exception e){}
            }
        }catch (Exception e){
            Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void Cont(View continuar){
        Intent continua = new Intent(this, Produto.class);
        startActivity(continua);
    }


}