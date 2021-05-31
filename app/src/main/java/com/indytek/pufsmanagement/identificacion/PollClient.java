package com.indytek.pufsmanagement.identificacion;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Tipo;
import com.indytek.pufsmanagement.objects.Usuario;

import java.util.List;

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


    public LiveData<Usuario> getLogin(String username, String pass) {
        if (login == null) {
            login = new MutableLiveData<Usuario>();
            loginUser(username, pass);
        }
        return login;
    }

    public LiveData<Usuario> getRegister(Usuario usuario) {
        if (register == null) {
            register = new MutableLiveData<Usuario>();
            registerUser(usuario);
        }
        return register;
    }

    public LiveData<List<Producto>> getProductos(Tipo tipo, Rango rango) {
        if (productos == null) {
            productos = new MutableLiveData<List<Producto>>();

        }
        return productos;
    }

    public PollService getApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.URLSERVIDOR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PollService service = retrofit.create(PollService.class);
        return service;
    }

    private void productosPorTipoYRango(Tipo tipo, Rango rango){
        PollService service = getApiService();

        Call<List<Producto>> prods = service.productosPorTipoYRango(rango,tipo);

        prods.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(response.body() != null){
                    productos.setValue(response.body());
                }else{
                    //TODO: Poner Toast con mensaje de no hay productos
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {

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
                    //TODO: Poner Toast con mensaje de bad register
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                //TODO: Poner Toast con mensaje de bad register
            }
        });
    }



    private void loginUser(String username, String pass){
        PollService service = getApiService();

        Call<Usuario> usuarios = service.pubsLogin(username, pass);

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


    }
}

