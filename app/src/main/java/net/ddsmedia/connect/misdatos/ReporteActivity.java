package net.ddsmedia.connect.misdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText txtNombre, txtEmail, txtTelefono, txtReporte;

    private ReporteService service;

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
    }

    private void guardarDatos(){
        Call<CallResult> llamadaGuardar = service.agregarReporte(
                txtNombre.getText().toString(),
                txtEmail.getText().toString(),
                txtTelefono.getText().toString(),
                txtReporte.getText().toString());
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
}
