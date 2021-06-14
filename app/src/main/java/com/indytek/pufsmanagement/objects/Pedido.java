package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString



public class Pedido implements Serializable {
	
	@EqualsAndHashCode.Include
    @SerializedName("id")
	private int id;

	@SerializedName ("dateOrdered")
	private LocalDateTime dateOrdered;

	@SerializedName("android")
	private boolean android;

	@SerializedName("price")
	private float precio;
	@SerializedName("pay")
	private float pay;
	@SerializedName("exchange")
	private float exchange;

	@SerializedName("notes")
	private String notes;

	@SerializedName("payMethod")
	private MetodoDePago payMethod;
	//cambiado de productos a integer por que solo es necesario la id para luego mostrar la informacion con consultas
	@Singular
	@SerializedName("products")
	private List<Producto> products;

	@SerializedName("cliUsername")
	private String username;
	
}
