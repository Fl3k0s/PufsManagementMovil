package com.indytek.pufsmanagement.identificacion;

import com.indytek.pufsmanagement.PedidoSerialize;
import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Tipo;
import com.indytek.pufsmanagement.objects.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PollService {

    @GET(Urls.PUBSLOGIN)
    public Call<Usuario> pubsLogin(@Query("user") String user, @Query("password") String password);

    @POST(Urls.REGISTER)
    public Call<Usuario> pubsRegister(@Body Usuario usuario);

    @POST(Urls.HACERPEDIDO)
    public Call<Pedido> realizarPedido(@Body Pedido pedido);

    @POST(Urls.HACERPEDIDOSERIALIZE)
    public Call<Pedido> realizarPedidoSerialize(@Body PedidoSerialize pedido);

    @GET(Urls.PEDIDOSPORUSER)
    public Call<List<Pedido>> pedidosPorUsuario(@Query("user") String user);

    @GET(Urls.PRODUCTOSPORTIPOYRANGO)
    public Call<List<Producto>> productosPorTipoYRango(@Query("rango") Rango rango, @Query("tipo") Tipo tipo);

    @PUT("")
    public Call<Usuario> actualizarUsuario(@Body Usuario usuario);

    @PUT(Urls.CANCELPEDIDO)
    public void cancelarPedido(@Query("user")String user, @Query("id") int id);

}

