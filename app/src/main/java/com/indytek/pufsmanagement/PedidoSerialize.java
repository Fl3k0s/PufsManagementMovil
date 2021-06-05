package com.indytek.pufsmanagement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class PedidoSerialize {

    @SerializedName("android")
    private boolean android;

    @SerializedName("price")
    private float price;

    @SerializedName("pay")
    private float pay;

    @SerializedName("exchange")
    private float exchange;

    @SerializedName("notes")
    private String notes;
    //cambiado de productos a integer por que solo es necesario la id para luego mostrar la informacion con consultas

    @SerializedName("payMethod")
    private int payMethod;

    @SerializedName("products")
    private List<Integer> products;

    @SerializedName("username")
    private String username;
}
