package com.disney.mundo.Model.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaSerieGuardarDTO {
    @NotNull
    Integer id;
    String imagen;
    String titulo;
    String fechaCreacion;
    Integer calificacion;
    Integer idgenero;

    List<Integer> personajes;
}
