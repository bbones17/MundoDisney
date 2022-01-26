package com.disney.mundo.Model.Mappers;

import com.disney.mundo.Model.DTO.PersonajeListadoDTO;
import com.disney.mundo.Model.Entities.Personaje;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class PersonajeListadoMapper {

    public PersonajeListadoDTO entityToDTO(Personaje entity) {
        return Optional
                .ofNullable(entity)
                .map(
                        ent -> new PersonajeListadoDTO(
                                ent.getId(),
                                ent.getImagen(),
                                ent.getNombre()
                        )
                )
                .orElse(new PersonajeListadoDTO());
    }

    public Personaje DTOToEntity(PersonajeListadoDTO dto) {
        Personaje entity = new Personaje();
        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setNombre((dto.getNombre()));
        return entity;
    }

}
