package com.indytek.pufsmanagement.identificacion;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
public class LoginRequest {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

}

