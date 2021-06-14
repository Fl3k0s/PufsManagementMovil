package com.indytek.pufsmanagement.identificacion;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.indytek.pufsmanagement.PedidoSerialize;
import com.indytek.pufsmanagement.objects.MetodoDePago;
import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Persona;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Tipo;
import com.indytek.pufsmanagement.objects.Usuario;
import com.indytek.pufsmanagement.objects.UsuarioSerilize;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PollClient {
    //llamadas a la api
    private MutableLiveData<Usuario> login;
    private MutableLiveData<Usuario> register;
    private MutableLiveData<List<Producto>> productos;
    private MutableLiveData<List<Pedido>> pedidos;
    private MutableLiveData<Usuario> usuarioModificado;
    private MutableLiveData<Pedido> nuevoPedido;
    private MutableLiveData<Pedido> pedidoCancelado;

    public LiveData<Pedido> cancelPedido(String user, int id){
            pedidoCancelado = new MutableLiveData<Pedido>();
            cancelarPedido(user, id);

        return pedidoCancelado;
    }

    public LiveData<Usuario> getLogin(String username, String pass) {

            login = new MutableLiveData<Usuario>();
            loginUser(username, pass);

        return login;
    }

    public LiveData<Usuario> updateUser(Usuario u) {

            login = new MutableLiveData<Usuario>();
            updatearUsuario(u);

        return login;
    }

    public LiveData<Usuario> getRegister(Usuario usuario) {
            register = new MutableLiveData<Usuario>();
            registerUser(usuario);

        return register;
    }

    public LiveData<Pedido> getNuevoPedido(Pedido pedido) {
            nuevoPedido = new MutableLiveData<Pedido>();
            newPedido(pedido);

        return nuevoPedido;
    }

    public LiveData<Usuario> newPubsRegister (UsuarioSerilize usuario) {

            register = new MutableLiveData<Usuario>();
            registerPubsUser(usuario);

        return register;
    }

    public LiveData<Pedido> getNuevoPedidoSerilize(PedidoSerialize pedido) {
            nuevoPedido = new MutableLiveData<Pedido>();
            newPedidoSerialize(pedido);

        return nuevoPedido;
    }

    public LiveData<List<Producto>> getProductos(Tipo tipo, Rango rango) {

            productos = new MutableLiveData<List<Producto>>();
            productosPorTipoYRango(tipo, rango);

        return productos;
    }

    public LiveData<List<Pedido>> getPedidos(String usuario) {
            pedidos = new MutableLiveData<List<Pedido>>();
            pedidosDeUnUsuario(usuario);

        return pedidos;
    }

    public PollService getApiService(){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDateTime.parse(json.getAsString()); }

        }).registerTypeAdapter(Producto[].class, new JsonDeserializer<Producto[]>() {
            @Override
            public Producto[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonArray obj = json.getAsJsonArray();
                Gson g = new Gson();

                Producto[] p = g.fromJson(obj, Producto[].class);

                System.out.println(p );
                return p;
            }
        }).registerTypeAdapter(Producto.class, new JsonDeserializer<Producto>() {
            @Override
            public Producto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                Gson g = new Gson();

                Producto p = g.fromJson(obj, Producto.class);
                System.out.println(obj.toString() + "hola");
                System.out.println(p + "hola");
                return p;
            }
        }).registerTypeAdapter(Persona.class, new JsonDeserializer<Persona>() {
            @Override
            public Persona deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject obj = json.getAsJsonObject();
                Gson g = new Gson();
                Persona p = g.fromJson(obj, Persona.class);
                return p;
            }
        }).registerTypeAdapter(Pedido[].class, new JsonDeserializer<Pedido[]>() {
            @Override
            public Pedido[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonArray obj = json.getAsJsonArray();
                Gson g = new Gson();
                Pedido[] p = g.fromJson(obj, Pedido[].class);
                return p;
            }
        }).create();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.URLSERVIDOR)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PollService service = retrofit.create(PollService.class);
        return service;
    }

    private void updatearUsuario(Usuario u){
        PollService service = getApiService();
        Call<Usuario> usuarios = service.actualizarUsuario(u);

        usuarios.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.body() != null){
                    register.setValue(response.body());
                    System.out.println("");
                }else{
                    //TODO: Poner Toast con mensaje de bad register
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                //TODO: Poner Toast con mensaje de bad register
                Log.d("Error acceso datos", t.getMessage());
            }
        });

    }
    private void pedidosDeUnUsuario(String usuario){
        PollService service = getApiService();

        Call<List<Pedido>> peds = service.pedidosPorUsuario(usuario);

        peds.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.body() != null){
                    pedidos.setValue(response.body());
                    System.out.println(response.body().size() + "");
                }else{
                    System.out.println("puta mierda");
                    //TODO: Poner Toast con mensaje de no hay productos
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Log.d("Error acceso datos", t.getMessage());
            }
        });
    }

    private void productosPorTipoYRango(Tipo tipo, Rango rango){
        PollService service = getApiService();

        Call<List<Producto>> prods = service.productosPorTipoYRango(rango,tipo);

        prods.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(response.body() != null){
                    productos.setValue(response.body());
                    System.out.println(response.body().size() + "");

                }else{
                    System.out.println("no hay productos");
                    //TODO: Poner Toast con mensaje de no hay productos
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.d("Error acceso datos", t.getMessage());
            }
        });
    }

    //TODO: Arreglar el agregar pedido
    private void newPedido(Pedido p){
        PollService service = getApiService();
        Call<Pedido> pedido = service.realizarPedido(p);

        pedido.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if(response.body() != null){
                    nuevoPedido.setValue(response.body());
                }else{
                    System.out.println("is null");
                    //TODO: Poner Toast con mensaje de bad register
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {

            }
        });
    }

    private void newPedidoSerialize(PedidoSerialize p){
        PollService service = getApiService();
        Call<Pedido> pedido = service.realizarPedidoSerialize(p);

        pedido.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                System.out.println(response.message());
                if(response.body() != null){
                    nuevoPedido.setValue(response.body());
                }else{
                    System.out.println("is null pedido");
                    //TODO: Poner Toast con mensaje de bad register
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {

            }
        });
    }

    private void registerUser(Usuario u){
        PollService service = getApiService();
        Call<Usuario> usuarios = service.pubsRegister(u);

        usuarios.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.body() != null){
                    register.setValue(response.body());
                }else{
                    System.out.println("is null");
                    //TODO: Poner Toast con mensaje de bad register
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                //TODO: Poner Toast con mensaje de bad register
                Log.d("Error acceso datos", t.getMessage());
            }
        });
    }
    private void registerPubsUser(UsuarioSerilize u){
        PollService service = getApiService();
        Call<Usuario> usuarios = service.pubsRegister2(u);

        usuarios.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.body() != null){
                    register.setValue(response.body());
                }else{
                    System.out.println("is null");
                    //TODO: Poner Toast con mensaje de bad register
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                //TODO: Poner Toast con mensaje de bad register
                Log.d("Error acceso datos", t.getMessage());
            }
        });
    }

    private void cancelarPedido(String user, int id){
        PollService service = getApiService();
        Call<Pedido> usuarios = service.cancelarPedido(user, id);

        usuarios.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if (response.body() != null){
                    System.out.println(response.body());
                }
                else System.out.println("error borrando");
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.d("Error acceso datos", t.getMessage());
            }
        });
    }



    private void loginUser(String username, String pass){
        PollService service = getApiService();

        Call<Usuario> usuarios = service.pubsLogin(username, pass);

        try {
            JSONObject paramObject = new JSONObject();



            usuarios.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.body() != null){
                        login.postValue(response.body());
                    }else{
                        //TODO: Poner Toast con mensaje de bad login
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    //TODO: Poner Toast con mensaje de bad login
                    Log.d("Error acceso datos", t.getMessage());
                }
            });
        }catch (Exception e){

        }



    }
}

