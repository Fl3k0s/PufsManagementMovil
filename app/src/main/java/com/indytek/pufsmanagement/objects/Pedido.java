package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDate;
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
	private LocalDate dateOrdered;
	
	@SerializedName("dateReceived")
	private LocalDate dateReceived;
	
	@SerializedName("active")
	private boolean active;

	//cambiado de productos a integer por que solo es necesario la id para luego mostrar la informacion con consultas
	@Singular
	@SerializedName("productos")
	private Set<Producto> products;
	
}
