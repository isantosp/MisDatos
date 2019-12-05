package net.ddsmedia.connect.misdatos.utils;

import net.ddsmedia.connect.misdatos.models.CallResult;
import net.ddsmedia.connect.misdatos.models.Empleado;
import net.ddsmedia.connect.misdatos.models.LoginResult;

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
                                    @Field("reporte") String reporte,
                                    // Agregamos campo geo que recibe y guarda
                                    // en el campo con el mismo nombre el WS
                                    @Field("geo") String geo);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResult> login(@Field("username") String usuario,
                            @Field("password") String contrasena);

}
