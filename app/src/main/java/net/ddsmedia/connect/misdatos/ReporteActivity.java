package net.ddsmedia.connect.misdatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import net.ddsmedia.connect.misdatos.models.CallResult;
import net.ddsmedia.connect.misdatos.utils.Globals;
import net.ddsmedia.connect.misdatos.utils.ReporteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReporteActivity extends AppCompatActivity {

    private SharedPreferences infoDatos;
    // Variables guardadas en sharedPreferences
    String mNombre, mEmail, mTelefono;

    // Variables de los campos de texto
    EditText txtNombre, txtEmail, txtTelefono, txtReporte, txtGeo;

    private ReporteService service;
    private FusedLocationProviderClient provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        infoDatos = getSharedPreferences(
                "misDatos",
                Context.MODE_PRIVATE);
        mNombre = infoDatos.getString("nombre","");
        mEmail = infoDatos.getString("email", "");
        mTelefono = infoDatos.getString("telefono", "");

        txtNombre = findViewById(R.id.rptNombre);
        txtEmail = findViewById(R.id.rptEmail);
        txtTelefono = findViewById(R.id.rptTelefono);
        txtReporte = findViewById(R.id.rptReporte);
        txtGeo = findViewById(R.id.rptGeo);

        txtNombre.setText(mNombre);
        txtEmail.setText(mEmail);
        txtTelefono.setText(mTelefono);

        service = Globals.getApi().create(ReporteService.class);

        Button btnSend = findViewById(R.id.btnEnviarRpt);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });

        // El problema del martes 3 fue que primero mandabamos a llamar
        // al método getPermisos() que a su vez manda a llamar si es que
        // ya tiene o se conceden los permisos se manda a llamar al método
        // getUbicacion() en donde usamos la variable provider que es la
        // que no estaba inicializada por que la linea siguiente estaba
        // después de getPermisos()
        provider = new FusedLocationProviderClient(this);
        getPermisos();


    }

    private void getUbicacion(){
        provider.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            txtGeo.setText(location.getLatitude()+","+
                                    location.getLongitude());
                        }
                    }
                });
    }

    private void guardarDatos(){
        Call<CallResult> llamadaGuardar = service.agregarReporte(
                txtNombre.getText().toString(),
                txtEmail.getText().toString(),
                txtTelefono.getText().toString(),
                txtReporte.getText().toString(),
                // Agregamo el parametro con lo que tiene txtGeo
                txtGeo.getText().toString());
        llamadaGuardar.enqueue(new Callback<CallResult>() {
            @Override
            public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                if(response.isSuccessful()){
                    CallResult resultado = response.body();
                    if(!resultado.isError()){
                        Toast.makeText(getApplicationContext(),
                                "Se agrego el reporte No: "+resultado.getId(),
                                Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<CallResult> call, Throwable t) {

            }
        });
    }


    private final int PERMISO_USUARIO_LOCALIZACION = 1;
    private void getPermisos(){
        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            String[] permisos = {Manifest.permission.ACCESS_COARSE_LOCATION};

            ActivityCompat.requestPermissions(this,
                    permisos,
                    PERMISO_USUARIO_LOCALIZACION);

        }else{
            //txtGeo.setText("YA TIENE PERMISO");
            getUbicacion();
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults){
        switch (requestCode){
            case PERMISO_USUARIO_LOCALIZACION:{
                if(grantResults.length > 0  &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //txtGeo.setText("SI DIO PERMISO");
                    getUbicacion();
                }else{
                    txtGeo.setText("NO DIO PERMISO");
                }
            }
        }
    }
}
