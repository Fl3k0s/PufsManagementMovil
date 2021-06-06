package com.indytek.pufsmanagement.objects;

import lombok.*;

import java.io.Serializable;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PersonaSerialize implements Serializable {
    @EqualsAndHashCode.Include
    private int id;

    private String dni;

    private String name;

    private String secondName1;

    private String secondName2;

    private String email;
}
