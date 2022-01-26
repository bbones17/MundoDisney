package com.disney.mundo.Model.Mappers;

import com.disney.mundo.Model.DTO.PeliculaSerieDTO;
import com.disney.mundo.Model.DTO.PeliculaSerieGuardarDTO;
import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.disney.mundo.Model.Entities.Personaje;
import com.disney.mundo.Model.Repositories.PersonajeRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class PeliculaSerieMapper {

    private final PersonajeRepository personajeRepository;
    public PeliculaSerieMapper (PersonajeRepository personajeRepository)
    {
        this.personajeRepository=personajeRepository;
    }

    public PeliculaSerieDTO entityToDTO(PeliculaSerie entity) {
        return Optional
                .ofNullable(entity)
                .map(
                        ent -> new PeliculaSerieDTO(
                                ent.getId(),
                                ent.getImagen(),
                                ent.getTitulo(),
                                String.valueOf(ent.getFecha_creacion()),
                                ent.getCalificacion(),
                                ent.getPersonajes()
                        )
                )
                .orElse(new PeliculaSerieDTO());
    }

    public PeliculaSerie DTOToEntity(PeliculaSerieDTO dto) {
        PeliculaSerie entity = new PeliculaSerie();
        entity.setId((dto.getId()));
        entity.setImagen(dto.getImagen());
        entity.setTitulo((dto.getTitulo()));
        entity.setFecha_creacion(Date.valueOf(dto.getFechaCreacion()));
        entity.setCalificacion(dto.getCalificacion());
        entity.setPersonajes(dto.getPersonajes());
        return entity;
    }

    public PeliculaSerie DTOGuardarToEntity(PeliculaSerieGuardarDTO dtoGuardar) {

        PeliculaSerie entity = new PeliculaSerie();

        //obtiene las personajes desde los idpersonaje
        ArrayList<Personaje> personajesList=new ArrayList<>();
        for(Integer i: dtoGuardar.getPersonajes())
        {
            personajesList.add(personajeRepository.findById(i).get());
        }

        entity.setId((dtoGuardar.getId()));
        entity.setImagen(dtoGuardar.getImagen());
        entity.setTitulo((dtoGuardar.getTitulo()));
        entity.setFecha_creacion(Date.valueOf(dtoGuardar.getFechaCreacion()));
        entity.setCalificacion(dtoGuardar.getCalificacion());
        entity.setPersonajes(personajesList);
       // entity.setIdGenero(dtoGuardar.getIdgenero());
        return entity;

    }
}
