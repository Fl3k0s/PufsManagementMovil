package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private static int count = 0;

    @EqualsAndHashCode.Include
    private int id;
    @SerializedName("nombre")
    private String nombre;
    //la imagen es una url para su busqueda
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("precio")
    private float precio;
    @SerializedName("rango")
    private Rango rango;

}
