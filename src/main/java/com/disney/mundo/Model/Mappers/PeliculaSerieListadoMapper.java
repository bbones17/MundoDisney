package com.disney.mundo.Model.Mappers;

import com.disney.mundo.Model.DTO.PeliculaSerieListadoDTO;
import com.disney.mundo.Model.Entities.PeliculaSerie;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
public class PeliculaSerieListadoMapper {

    public PeliculaSerieListadoDTO entityToDTO(PeliculaSerie entity) {
        return Optional
                .ofNullable(entity)
                .map(
                        ent -> new PeliculaSerieListadoDTO(
                                ent.getId(),
                                ent.getImagen(),
                                ent.getTitulo(),
                                String.valueOf(ent.getFecha_creacion())
                        )
                )
                .orElse(new PeliculaSerieListadoDTO());
    }

    public PeliculaSerie DTOToEntity(PeliculaSerieListadoDTO dto) {
        PeliculaSerie entity = new PeliculaSerie();
        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFecha_creacion(Date.valueOf(dto.getFechaCreacion()));
        return entity;
    }
    
    
    
}
