package com.indytek.pufsmanagement.identificacion;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PollClient {
    private MutableLiveData<LoginResponse> login;
    public LiveData<LoginResponse> getLogin(LoginRequest request) {
        if (login == null) {
            login = new MutableLiveData<LoginResponse>();
            loadLogin(request);
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
    private void loadLogin(LoginRequest request) {
        // Accede al API para descargar datos
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.URLSERVIDOR)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PollService service = retrofit.create(PollService.class);
        //le pido al servicio que me devuelva las Question y le pasamos su token para que pueda obtenerlas
        Call<LoginResponse> repos = service.login(request);



        repos.enqueue(new Callback<LoginResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //si responde le damos valor
                login.postValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("Error acceso datos", t.getMessage());

            }
        });


    }
}

