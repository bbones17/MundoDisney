package com.disney.mundo.Services;


import com.disney.mundo.Exceptions.NotFoundException;
import com.disney.mundo.Exceptions.ValidationException;
import com.disney.mundo.Model.DTO.GeneroDTO;
import com.disney.mundo.Model.DTO.GeneroDTO;
import com.disney.mundo.Model.DTO.GeneroGuardarDTO;
import com.disney.mundo.Model.DTO.GeneroDTO;
import com.disney.mundo.Model.Entities.Genero;
import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.disney.mundo.Model.Mappers.GeneroMapper;
import com.disney.mundo.Model.Repositories.GeneroRepository;
import com.disney.mundo.Model.Repositories.PeliculaSerieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("generoService")
public class GeneroService  {


    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;
    private final PeliculaSerieRepository peliculaSerieRepository;

    public GeneroService(GeneroRepository generoRepository, GeneroMapper generoMapper,PeliculaSerieRepository peliculaSerieRepository)
    {
        this.generoRepository=generoRepository;
        this.peliculaSerieRepository=peliculaSerieRepository;
        this.generoMapper=generoMapper;
    }


    public Page<GeneroDTO> findAll(Integer pageNumber, Integer pageSize, String orderField) {
        if (Objects.equals(orderField, null)) {
            Pageable pageable = PageRequest.of(0, 10);
            return generoRepository.findAll(pageable).map(generoMapper::entityToDTO);

        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orderField));
            return generoRepository.findAll(pageable).map(generoMapper::entityToDTO);
        }
    }


    public GeneroDTO save(GeneroGuardarDTO dto) {
        if ((Objects.equals(dto.getNombre(), null))
                ||
                (!Objects.isNull((peliculaSerieRepository.findByTitulo(dto.getNombre()))))
            )
        {
            throw new ValidationException("Error en los datos o ya existe");
        }
        else
        {
            dto.setId(null);
            return generoMapper
                    .entityToDTO(generoRepository.save(generoMapper.DTOGuardarToEntity(dto)));
        }
    }

    
}
