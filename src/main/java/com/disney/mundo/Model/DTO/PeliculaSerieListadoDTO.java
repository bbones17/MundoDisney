package com.disney.mundo.Model.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaSerieListadoDTO {
    @NotNull
    Integer id;
    String imagen;
    String titulo;
    String fechaCreacion;

}