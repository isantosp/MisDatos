package net.ddsmedia.connect.misdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.ddsmedia.connect.misdatos.models.LoginResult;
import net.ddsmedia.connect.misdatos.utils.Globals;
import net.ddsmedia.connect.misdatos.utils.ReporteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUser, txtPass;
    private Button btnLogin;

    private ReporteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);

        service = Globals.getApi().create(ReporteService.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin(){
        String usuario = txtUser.getText().toString();
        String contrasena = txtPass.getText().toString();

        if(usuario.isEmpty()){
            Toast.makeText(this, "Falta el usuario", Toast.LENGTH_SHORT).show();
        }else if(contrasena.isEmpty()){
            Toast.makeText(this, "Falta la contraseña", Toast.LENGTH_SHORT).show();
        }else{
            Call<LoginResult> login = service.login(usuario, contrasena);
            login.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    if(response.isSuccessful()){
                        LoginResult resultado = response.body();
                        if(resultado.isError()){
                            Toast.makeText(getApplicationContext(),
                                    resultado.getMensaje(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }else{
                            Toast.makeText(getApplicationContext(),
                                    resultado.getMensaje(),
                                    Toast.LENGTH_LONG)
                                    .show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {

                }
            });
        }

    }
}
