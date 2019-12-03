package net.ddsmedia.connect.misdatos.utils;

import net.ddsmedia.connect.misdatos.models.CallResult;
import net.ddsmedia.connect.misdatos.models.Empleado;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReporteService {

    // http://isantosp.com/cursoAndroid/getEmpleado.php?id=5
    @GET("getEmpleado.php")
    Call<Empleado> getEmpleadoUnico(@Query("id") int idEmpleado);


    @FormUrlEncoded
    @POST("addReporte.php")
    Call<CallResult> agregarReporte(@Field("nombre") String nombre,
                                    @Field("email") String email,
                                    @Field("telefono") String telefono,
                                    @Field("reporte") String reporte);

}
