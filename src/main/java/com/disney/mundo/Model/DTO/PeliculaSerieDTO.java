package com.disney.mundo.Model.DTO;

import com.disney.mundo.Model.Entities.Genero;
import com.disney.mundo.Model.Entities.Personaje;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaSerieDTO {

    @NotNull
    Integer id;
    String imagen;
    String titulo;
    String fechaCreacion;
    Integer calificacion;

    List<Personaje> personajes;
}
