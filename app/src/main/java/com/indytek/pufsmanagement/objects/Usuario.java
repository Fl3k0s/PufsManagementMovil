package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString



/*
Clase de usuario
 */
public class Usuario{

    @NonNull
    @EqualsAndHashCode.Include
    @SerializedName("id")
    private int id;

    @NonNull
    @SerializedName("username")
    private String username;

    @NonNull
    @SerializedName("password")
    private String password;

    @SerializedName("rango")
    private Rango rango;

    //varios usuarios podran tener la misma direccion
    @SerializedName("direccion")
    private Direccion direccion;

    @Singular
    @SerializedName("orders")
    private Set<Pedido> orders;

}