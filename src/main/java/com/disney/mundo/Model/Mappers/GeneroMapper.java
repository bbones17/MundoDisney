package com.disney.mundo.Model.Mappers;


import com.disney.mundo.Model.DTO.GeneroDTO;
import com.disney.mundo.Model.DTO.GeneroGuardarDTO;
import com.disney.mundo.Model.Entities.Genero;
import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.disney.mundo.Model.Repositories.PeliculaSerieRepository;
import com.disney.mundo.Services.PeliculaSerieService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GeneroMapper {


    private final PeliculaSerieRepository peliculaSerieRepository;
    public GeneroMapper(PeliculaSerieRepository peliculaSerieRepository)
    {
        this.peliculaSerieRepository=peliculaSerieRepository;
    }


    public GeneroDTO entityToDTO(Genero entity) {
        return Optional
                .ofNullable(entity)
                .map(
                        ent -> new GeneroDTO(
                                ent.getId(),
                                ent.getImagen(),
                                ent.getNombre(),
                                ent.getPeliculasDelGenero()
                        )
                )
                .orElse(new GeneroDTO());
    }

    public Genero DTOToEntity(GeneroDTO dto) {
        Genero entity = new Genero();

        entity.setId((dto.getId()));
        entity.setImagen(dto.getImagen());
        entity.setNombre((dto.getNombre()));
        entity.setPeliculasDelGenero(dto.getPeliculaSerie());
        return entity;
    }


    public Genero DTOGuardarToEntity(GeneroGuardarDTO dto) {
        Genero entity = new Genero();

        //obtiene las peliculas desde los idpelicula
        ArrayList<PeliculaSerie> peliculaSerieList=new ArrayList<>();
        for(Integer i: dto.getPeliculaSerie())
        {
            peliculaSerieList.add(peliculaSerieRepository.findById(i).get());
        }

        entity.setId((dto.getId()));
        entity.setImagen(dto.getImagen());
        entity.setNombre((dto.getNombre()));
        entity.setPeliculasDelGenero(peliculaSerieList);
        return entity;
    }



}
