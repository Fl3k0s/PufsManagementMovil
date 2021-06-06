package com.indytek.pufsmanagement.objects;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class UsuarioSerilize implements Serializable {
    @NonNull
    @EqualsAndHashCode.Include
    private int id;

    private String username;

    private String password;

    private Rango rango;

    private Direccion direccion;

    private List<Pedido> orders;

    private PersonaSerialize person;

}
