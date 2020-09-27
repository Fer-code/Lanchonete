package com.example.lanchonete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


public class Local extends AppCompatActivity implements
        FetchAddressTask.OnTaskCompleted {

    public static final String PREFERENCIAS_NAME = "com.example.android.localizacao";
    public final static String ENDERECO = "com.example.lanchonete.END";
    private static final String TRACKING_LOCATION_KEY = "tracking_location";

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";

    private Button btnmLocation;
    private TextView txtMLocation;
    private EditText txtEnd;
    private static final String LASTADRESS_KEY = "adress";
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private boolean mTrackingLocation;

    private SharedPreferences mPreferences;
    private String lastLatitude = "";
    private String lastLongitude = "";
    private String lastAdress = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        getSupportActionBar().hide();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(
                this);

        btnmLocation = (Button) findViewById(R.id.btLocal);
        txtMLocation = (TextView) findViewById(R.id.txtLocal);

        if (savedInstanceState != null) {
            mTrackingLocation = savedInstanceState.getBoolean(
                    TRACKING_LOCATION_KEY);
            txtEnd.setText(savedInstanceState.getString(ENDERECO));
        }

        btnmLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!mTrackingLocation) {
                    startTrackingLocation();
                } else {
                    stopTrackingLocation();
                }
            }
        });

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {

                if (mTrackingLocation) {
                    new FetchAddressTask(Local.this, Local.this)
                            .execute(locationResult.getLastLocation());
                }
            }
        };

        mPreferences = getSharedPreferences(PREFERENCIAS_NAME, MODE_PRIVATE);
        recuperar();

    }

    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates
                    (getLocationRequest(),
                            mLocationCallback,
                            null);

            txtMLocation.setText(getString(R.string.address_text,
                    getString(R.string.carregando), null, null));
            btnmLocation.setText(R.string.buscar);

        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private void stopTrackingLocation() {
        if (mTrackingLocation) {
            mTrackingLocation = false;
            btnmLocation.setText(R.string.iniciar);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(TRACKING_LOCATION_KEY, mTrackingLocation);
        super.onSaveInstanceState(outState);
        outState.putString(ENDERECO, txtEnd.getText().toString());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startTrackingLocation();
                } else {
                    Toast.makeText(this, R.string.permissao_negada,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onTaskCompleted(String[] result) {
        if (mTrackingLocation) {
            // Update the UI
            lastLatitude = result[1];
            lastLongitude = result[2];
            lastAdress = result[0];
            txtMLocation.setText(getString(R.string.address_text,
                    lastAdress, lastLatitude, lastLongitude));
        }
    }


    private void armazenar(String latitude, String longitude, String lastAdress) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(LATITUDE_KEY, latitude);
        preferencesEditor.putString(LONGITUDE_KEY, longitude);
        preferencesEditor.putString(LASTADRESS_KEY, lastAdress);
        preferencesEditor.apply();
    }

    private void recuperar() {

        lastLatitude = mPreferences.getString(LATITUDE_KEY, "");
        lastLongitude = mPreferences.getString(LONGITUDE_KEY, "");
        lastAdress = mPreferences.getString(LASTADRESS_KEY, "");

    }
    public void continuar(View continuar){
         txtEnd = findViewById(R.id.textEnd);
        txtMLocation = findViewById(R.id.txtLocal);

        String txtEnd1 = txtEnd.getText().toString();
        String txtLocal = txtMLocation.getText().toString();


        if(txtEnd1.isEmpty() && txtLocal.isEmpty()){
            Toast.makeText(this, "Precisa escolher uma das opções", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Endereço Registrado com sucesso estamos a caminho", Toast.LENGTH_SHORT).show();
        }
    }
}