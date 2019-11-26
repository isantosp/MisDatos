package net.ddsmedia.connect.misdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DatosActivity extends AppCompatActivity {

    private SharedPreferences infoDatos;
    // Variables guardadas en sharedPreferences
    String mNombre, mEmail, mTelefono;

    // Variables de los campos de texto
    EditText txtNombre, txtEmail, txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        infoDatos = getSharedPreferences(
                "misDatos",
                Context.MODE_PRIVATE);
        mNombre = infoDatos.getString("nombre","");
        mEmail = infoDatos.getString("email", "");
        mTelefono = infoDatos.getString("telefono", "");

        txtNombre = findViewById(R.id.txtNombre);
        txtEmail = findViewById(R.id.txtMail);
        txtTelefono = findViewById(R.id.txtTelefono);

        txtNombre.setText(mNombre);
        txtEmail.setText(mEmail);
        txtTelefono.setText(mTelefono);
    }

    public void guardarDatos(View view){
        if(view.getId() == R.id.btnGuardarDatos){
            SharedPreferences.Editor editor = infoDatos.edit();
            editor.putString("nombre", txtNombre.getText().toString());
            editor.putString("email", txtEmail.getText().toString());
            editor.putString("telefono", txtTelefono.getText().toString());
            editor.commit();
            Toast.makeText(this,
                    "Se guardaron los datos",
                    Toast.LENGTH_LONG).show();
        }
    }
}
