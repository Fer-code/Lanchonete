package com.example.lanchonete;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Cadastro extends AppCompatActivity {
/*
    public final static String EXTRA_MESSAGE_NOME = "com.example.lanchonete.MENSAGEM";
    public final static String EXTRA_MESSAGE_SENHA = "com.example.lanchonete.EMAI";

    ImageView imageViewFoto;*/
    EditText nome;
    EditText senha;
    EditText tel;
    EditText email;
    EditText conSenha;
    Button okay;

    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();


        nome = findViewById(R.id.nomeCad);
        email = findViewById(R.id.emailCad);
        tel = findViewById(R.id.TelCad);
        senha = findViewById(R.id.SenhaCad);
        conSenha = findViewById(R.id.ConfSenhaCad);
        okay = findViewById(R.id.btnCadastrar);

        /*okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha1 = senha.getText().toString();
                String nome1 = nome.getText().toString();
                String email1 = email.getText().toString();
                String tel1 = tel.getText().toString();
                String conSenha1 = conSenha.getText().toString();
            }
        });*/



        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome1 = nome.getText().toString();
                String telefone = tel.getText().toString();
                String email1 = email.getText().toString();
                String senha1 = senha.getText().toString();
                String consenha1 = conSenha.getText().toString();

                if(senha1.isEmpty() || nome1.isEmpty() ||  email1.isEmpty() || telefone.isEmpty() || consenha1.isEmpty() ){
                    Toast.makeText(Cadastro.this, "Insira os dados corretamente", Toast.LENGTH_SHORT).show();
                }
                 else if  (!senha1.equals(consenha1)){
                    Toast.makeText(Cadastro.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();
                }
                 /*else if (db.Validacaoemail(email1)) {
                    Toast.makeText(Cadastro.this, "Email já utilizado", Toast.LENGTH_SHORT).show();

                }*/
                 else {
                    db.addUsuario(new Usuario(nome1, telefone, email1, senha1));
                    Toast.makeText(Cadastro.this, "Cliente adicionado com sucesso", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Cadastro.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        Usuario usuario = db.selecionarUsuario(1);
        Log.d("Cliente Selecionado ", "cod " + usuario.getCodigo() + " nome " + usuario.getNome()
                + " Telefone " + usuario.getTelefone() + " Email " + usuario.getEmail() + " Senha: " + usuario.getSenha());




       /* if (ActivityCompat.checkSelfPermission(Cadastro.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Cadastro.this, new String[]{Manifest.permission.CAMERA}, 0);
        }
        imageViewFoto = (ImageView) findViewById(R.id.imageView);*/
    }
/*
    public void camera(View b) {
        tirarFoto();
    }

    public void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);

        }
        super.onActivityResult(requestCode, resultCode, data);

    }*//*
    public void okay(View c) {

         nome = findViewById(R.id.nomeCad);
         email = findViewById(R.id.emailCad);
         tel = findViewById(R.id.TelCad);
         senha = findViewById(R.id.SenhaCad);
         conSenha = findViewById(R.id.ConfSenhaCad);*/

        /*imageViewFoto = findViewById(R.id.imageView);

        String senha1 = senha.getText().toString();
        String nome1 = nome.getText().toString();
        String email1 = email.getText().toString();
        String tel1 = tel.getText().toString();
        String conSenha1 = conSenha.getText().toString();

        if(senha1.isEmpty() || nome1.isEmpty() ||  email1.isEmpty() || tel1.isEmpty() || conSenha1.isEmpty() ){
            Toast.makeText(this, "Insira os dados corretamente", Toast.LENGTH_SHORT).show();
        }
        if  (!senha1.equals(conSenha1)){
            Toast.makeText(this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();
        }
        else{
            if(imageViewFoto != null) {
                try {
                    Drawable drawable = imageViewFoto.getDrawable();
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] imageInByte = stream.toByteArray();

                    Intent intent = new Intent(Cadastro.this, MainActivity.class);
                    intent.putExtra("imagem", imageInByte);
                    intent.putExtra(EXTRA_MESSAGE_NOME, nome1);
                    intent.putExtra(EXTRA_MESSAGE_SENHA, senha1);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(this,"Insira uma foto de perfil", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }*/
}