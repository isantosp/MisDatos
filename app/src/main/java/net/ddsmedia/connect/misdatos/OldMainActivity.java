package net.ddsmedia.connect.misdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OldMainActivity extends AppCompatActivity {

    private Button mBtnDatos;
    private Button mBtnDatosOtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);

        mBtnDatos = findViewById(R.id.btnDatos);

        mBtnDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OldMainActivity.this,
                        DatosActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickEnBotones(View view){
        switch (view.getId()){
            /*case R.id.btnDatos:
                Toast.makeText(getApplicationContext(),
                        "Hola soy el boton Datos desde fuera",
                        Toast.LENGTH_LONG).show();
                break;*/

            case R.id.otroBoton:
                Toast.makeText(getApplicationContext(),
                        "Hola soy el otro boton",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }


}
