package com.indytek.pufsmanagement.identificacion;

import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Tipo;
import com.indytek.pufsmanagement.objects.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PollService {

    @GET(Urls.PUBSLOGIN)
    public Call<Usuario> pubsLogin(@Query("user") String user, @Query("password") String password);

    @POST("")
    @FormUrlEncoded
    public Call<Usuario> pubsRegister(@Body Usuario usuario);

    @GET(Urls.PEDIDOSPORUSER)
    public Call<List<Pedido>> pedidosPorUsuario(@Path("user")String user);

    @GET("")
    public Call<List<Producto>> productosPorTipoYRango(@Body Rango rango, @Body Tipo tipo);

    @PUT("")
    public Call<Usuario> actualizarUsuario(@Body Usuario usuario);

}

