package com.disney.mundo.Model.Mappers;

import com.disney.mundo.Model.DTO.PersonajeDTO;
import com.disney.mundo.Model.DTO.PersonajeGuardarDTO;
import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.disney.mundo.Model.Entities.Personaje;
import com.disney.mundo.Model.Repositories.PeliculaSerieRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonajeMapper {


    private final PeliculaSerieRepository peliculaSerieRepository;
    public PersonajeMapper(PeliculaSerieRepository peliculaSerieRepository)
    {
        this.peliculaSerieRepository=peliculaSerieRepository;
    }


    public PersonajeDTO entityToDTO(Personaje entity) {

        return Optional
                .ofNullable(entity)
                .map(
                        ent -> new PersonajeDTO(
                                ent.getId(),
                                ent.getImagen(),
                                ent.getNombre(),
                                ent.getEdad(),
                                ent.getPeso(),
                                ent.getHistoria(),
                                ent.getPeliculaseries())
                )
                .orElse(new PersonajeDTO());
    }

    public Personaje DTOToEntity(PersonajeDTO dto) {
        Personaje entity = new Personaje();

        entity.setId((dto.getId()));
        entity.setImagen(dto.getImagen());
        entity.setNombre((dto.getNombre()));
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());
        entity.setPeliculaseries(dto.getPeliculaseries());
        return entity;

    }


    public Personaje DTOGuardarToEntity(PersonajeGuardarDTO dtoGuardar) {

        Personaje entity = new Personaje();

        //obtiene las peliculas desde los idpelicula
        ArrayList<PeliculaSerie> peliculaSerieList=new ArrayList<>();
        for(Integer i: dtoGuardar.getPeliculaseries())
        {
            peliculaSerieList.add(peliculaSerieRepository.findById(i).get());
        }

        entity.setId((dtoGuardar.getId()));
        entity.setImagen(dtoGuardar.getImagen());
        entity.setNombre((dtoGuardar.getNombre()));
        entity.setEdad(dtoGuardar.getEdad());
        entity.setPeso(dtoGuardar.getPeso());
        entity.setHistoria(dtoGuardar.getHistoria());
        entity.setPeliculaseries(peliculaSerieList);
        return entity;

    }




    
}
