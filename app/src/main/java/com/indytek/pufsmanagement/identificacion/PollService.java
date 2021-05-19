package com.indytek.pufsmanagement.identificacion;

import com.indytek.pufsmanagement.objects.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface PollService {

    @POST(Urls.URLLOGIN2)
    //@Headers({ "Content-Type: application/json;charset=UTF-8"})
    public Call<Usuario> login (@Body LoginRequest request);

    @POST(Urls.URLLOGIN)
    @FormUrlEncoded
    public Call<LoginResponse> login2(@Body LoginRequest request);

    @POST(Urls.URLTOKEN)
    public Call<PostResponse> fetchPost(@Header("Authorization")String token);

}

