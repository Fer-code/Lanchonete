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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE_NOME = "com.example.lanchonete.MENSAGEM";
    public final static String EXTRA_MESSAGE_EMAIL = "com.example.lanchonete.EMAI";

    private EditText nome;
    private EditText email;

    ImageView imageViewFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        nome = (EditText) findViewById(R.id.txtNome);
        email = (EditText) findViewById(R.id.txtEmail);

        if (savedInstanceState != null) {
            nome.setText(savedInstanceState.getString(EXTRA_MESSAGE_NOME));
            email.setText(savedInstanceState.getString(EXTRA_MESSAGE_EMAIL));
        }

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
        }
        imageViewFoto = (ImageView) findViewById(R.id.imageView);
    }


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

    }
    public void okay(View c) {

        EditText nome = findViewById(R.id.txtNome);
        EditText email = findViewById(R.id.txtEmail);
        imageViewFoto = findViewById(R.id.imageView);

        String email1 = email.getText().toString();
        String nome1 = nome.getText().toString();

        if(email1.isEmpty() || nome1.isEmpty() ){
            Toast.makeText(this, "Insira os dados corretamente", Toast.LENGTH_SHORT).show();
        }
        else{
            if(imageViewFoto != null) {
                try {
                    Drawable drawable = imageViewFoto.getDrawable();
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] imageInByte = stream.toByteArray();

                    Intent intent = new Intent(MainActivity.this, Vis_Cadastro.class);
                    intent.putExtra("imagem", imageInByte);
                    intent.putExtra(EXTRA_MESSAGE_NOME, nome1);
                    intent.putExtra(EXTRA_MESSAGE_EMAIL, email1);
                    startActivity(intent);

                }catch (Exception e){
                    Toast.makeText(this,"Insira uma foto de perfil", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstance) {
        super.onSaveInstanceState(saveInstance);
        saveInstance.putString(EXTRA_MESSAGE_NOME, nome.getText().toString());
        saveInstance.putString(EXTRA_MESSAGE_EMAIL, email.getText().toString());
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        super.onRestoreInstanceState(savedInstance);
        String nomeRecuperado = savedInstance.getString(EXTRA_MESSAGE_NOME);
        String emailRecuperado = savedInstance.getString(EXTRA_MESSAGE_EMAIL);
        nome.setText(nomeRecuperado);
        email.setText(emailRecuperado);
    }

}
