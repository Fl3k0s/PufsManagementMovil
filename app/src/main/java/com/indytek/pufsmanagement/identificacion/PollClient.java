package com.indytek.pufsmanagement.identificacion;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.indytek.pufsmanagement.objects.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PollClient {
    private MutableLiveData<Usuario> login;
    private MutableLiveData<Usuario> user;

    public LiveData<Usuario> getLogin(LoginRequest request) {
        user = new MutableLiveData<>();
        if (login == null) {
            login = new MutableLiveData<Usuario>();
            loginUser(request);
        }
        return login;
    }

    public PollService getApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.URLSERVIDOR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PollService service = retrofit.create(PollService.class);
        return service;
    }


    private void loginUser(LoginRequest request){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.URLSERVIDOR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PollService service = retrofit.create(PollService.class);
        Call<Usuario> usuarios = service.login(request);
        System.out.println("hola mundo");

        usuarios.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.body() != null){
                    user.postValue(response.body());
                }else
                    System.out.println("failed");
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("Error acceso datos", t.getMessage());
            }
        });


    }
}

