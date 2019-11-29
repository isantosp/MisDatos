package net.ddsmedia.connect.misdatos.utils;

import net.ddsmedia.connect.misdatos.models.Empleado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReporteService {

    // http://isantosp.com/cursoAndroid/getEmpleado.php?id=5
    @GET("getEmpleado.php")
    Call<Empleado> getEmpleadoUnico(@Query("id") int idEmpleado);

}
