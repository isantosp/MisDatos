package net.ddsmedia.connect.misdatos.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import net.ddsmedia.connect.misdatos.R;
import net.ddsmedia.connect.misdatos.models.Empleado;
import net.ddsmedia.connect.misdatos.utils.Globals;
import net.ddsmedia.connect.misdatos.utils.ReporteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Gson gson;
    private TextView txtRes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        gson = new Gson();
        final Empleado empleadoObjeto = new Empleado(1,
                "Juanito Perez",
                "Patito");


        final String jsonObjeto = "{ id: 4, nombre: \"Jaimito Hernandez\", empresa: \"ACME\" }";

        txtRes = root.findViewById(R.id.txtResultado);

        Button toJsonBtn = root.findViewById(R.id.btn2Json);
        toJsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                claseAJson(empleadoObjeto);
            }
        });

        Button fromJsonBtn = root.findViewById(R.id.btnFromJson);
        fromJsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonAClase(jsonObjeto);
            }
        });


        service = Globals.getApi().create(ReporteService.class);

        Button btnLlamada = root.findViewById(R.id.btnLlamada);
        btnLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEmpleado(5);
            }
        });


        return root;
    }

    private ReporteService service;
    private Call<Empleado> getEmpleadoCall;

    private void getEmpleado(int id){
        getEmpleadoCall = service.getEmpleadoUnico(id);
        getEmpleadoCall.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(response.isSuccessful()){
                    Empleado empResult = response.body();
                    claseAJson(empResult);
                }
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {

            }
        });

    }


    private void claseAJson(Empleado empleado){
        String resultado = gson.toJson(empleado);
        txtRes.setText(resultado);
    }

    private void jsonAClase(String json){
        Empleado empResult = gson.fromJson(json, Empleado.class);
        String resultado = "id: "+empResult.getId();
        resultado += "\nnombre: " + empResult.getNombre();
        resultado += "\nempresa: "+empResult.getEmpresa();
        txtRes.setText(resultado);
    }
}