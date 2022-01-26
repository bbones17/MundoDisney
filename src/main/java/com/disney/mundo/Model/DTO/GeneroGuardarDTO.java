package com.disney.mundo.Model.DTO;

import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroGuardarDTO {

    Integer id;
    String imagen;
    String nombre;

    List<Integer> peliculaSerie;
}
