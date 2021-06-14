package com.indytek.pufsmanagement.objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.gson.annotations.JsonAdapter;
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

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"

)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Comida.class, name = "food"),
        @JsonSubTypes.Type(value = Bebida.class, name = "drink")
})
/*
Clase padre de producto
 */
@JsonTypeName("product")
public class Producto {
    private static int count = 0;

    @EqualsAndHashCode.Include
    private int id;
    @SerializedName("name")
    private String nombre;

    //la imagen es una url para su busqueda
    @SerializedName("urlProducto")
    private String url_product;
    @SerializedName("pc")
    private float precio;
    @SerializedName("pvp")
    private float pvp;
    @SerializedName("stock")
    private int stock;
    @SerializedName("tipo")
    private Tipo tipo;
    @SerializedName("rango")
    private Rango rango;

}
