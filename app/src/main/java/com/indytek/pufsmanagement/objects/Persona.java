package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Set;


import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@ToString


/*
Clase padre de persona
 */
public class Persona implements Serializable {

	@EqualsAndHashCode.Include
    @SerializedName("id")
	private int id;
	@SerializedName("dni")
	private String dni;
	@SerializedName("name")
	private String name;

	@SerializedName("secondname1")
	private String secondName1;

	@SerializedName("secondname2")
	private String secondName2;

	//el email será unico
	@SerializedName("email")
	private String email;


	
}
