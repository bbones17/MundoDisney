package com.disney.mundo.Model.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonajeListadoDTO {
    @NotNull
    Integer id;
    String imagen;
    String nombre;
}
