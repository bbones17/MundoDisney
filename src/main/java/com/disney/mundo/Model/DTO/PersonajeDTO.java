package com.disney.mundo.Model.DTO;


import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonajeDTO {

    @NotNull
    Integer id;
    String imagen;
    String nombre;
    Integer edad;
    Double peso;
    String historia;

    List<PeliculaSerie> peliculaseries;


}
