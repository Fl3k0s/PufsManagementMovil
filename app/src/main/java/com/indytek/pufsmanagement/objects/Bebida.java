package com.indytek.pufsmanagement.objects;



import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)


/*
Clase de bebidas (PRODUCTO)
 */
public class Bebida extends Producto {

	@SerializedName ("volumen")
	private float volumen;

	//tamaño en litros de la bebida
	@SerializedName("tamanio")
	private float tamanio;

}
