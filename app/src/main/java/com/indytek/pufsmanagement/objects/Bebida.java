package com.indytek.pufsmanagement.objects;



import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)


/*
Clase de bebidas (PRODUCTO)
 */
public class Bebida extends Producto {
	
	@SerializedName("unidades")
	private int uds;

}
