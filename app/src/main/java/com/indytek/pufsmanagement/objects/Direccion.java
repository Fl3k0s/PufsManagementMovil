package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString



/*
Clase de direccion
 */
public class Direccion implements Serializable {


    @EqualsAndHashCode.Include
    @SerializedName("id")
    private int id;

    @SerializedName("calle")
    private String calle;

    //se pone como String porque puede haber numeros que son 5 bis
    @SerializedName("numero")
    private String numero;

    //se pone como String porque el portal puede ser una letra
    @SerializedName("portal")
    private String portal;

    @SerializedName("piso")
    private String piso;

    @SerializedName("puerta")
    private String puerta;
}
