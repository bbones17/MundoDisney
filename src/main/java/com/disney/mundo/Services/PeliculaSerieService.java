package com.disney.mundo.Services;


import com.disney.mundo.Exceptions.NotFoundException;
import com.disney.mundo.Exceptions.ValidationException;
import com.disney.mundo.Model.DTO.PeliculaSerieDTO;
import com.disney.mundo.Model.DTO.PeliculaSerieGuardarDTO;
import com.disney.mundo.Model.DTO.PeliculaSerieListadoDTO;
import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.disney.mundo.Model.Mappers.PeliculaSerieListadoMapper;
import com.disney.mundo.Model.Mappers.PeliculaSerieMapper;
import com.disney.mundo.Model.Repositories.PeliculaSerieRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("peliculaSerieService")
public class PeliculaSerieService {

    private final PeliculaSerieRepository peliculaSerieRepository;
    private final PeliculaSerieMapper peliculaSerieMapper;
    private final PeliculaSerieListadoMapper peliculaSerieListadoMapper;

    public PeliculaSerieService(PeliculaSerieRepository peliculaSerieRepository, PeliculaSerieMapper peliculaSerieMapper, PeliculaSerieListadoMapper peliculaSerieListadoMapper)
    {
        this.peliculaSerieRepository=peliculaSerieRepository;
        this.peliculaSerieMapper=peliculaSerieMapper;
        this.peliculaSerieListadoMapper=peliculaSerieListadoMapper;
    }
    

    public Page<PeliculaSerieDTO> findAll(Integer pageNumber, Integer pageSize, String orderField) {

        Pageable pageable;
        if(Objects.equals(orderField, null))
        {
            pageable = PageRequest.of(0, 10);

        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orderField));
        }
        return peliculaSerieRepository.findAll(pageable).map(peliculaSerieMapper::entityToDTO);
    }

    
    public Page<PeliculaSerieListadoDTO> findAllListado(Integer pageNumber, Integer pageSize, String orderField) {
        if (Objects.equals(orderField, null)) {
            Pageable pageable = PageRequest.of(0, 10);
            return peliculaSerieRepository.findAll(pageable).map(peliculaSerieListadoMapper::entityToDTO);

        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orderField));
            return peliculaSerieRepository.findAll(pageable).map(peliculaSerieListadoMapper::entityToDTO);
        }
    }


    
    public Page<PeliculaSerieDTO> findById(Integer id, Integer pageNumber, Integer pageSize, String orderField) {
        if (!peliculaSerieRepository.existsById(id)) {
            throw new NotFoundException("Pelicula o Serie no encontrada");
        } else {
        //    PeliculaSerieDTO dto = peliculaSerieMapper.entityToDTO(peliculaSerieRepository.findById(id).get());
            Pageable pageable= PageRequest.of(0,1);
            return peliculaSerieRepository
                    .findById(id,pageable)
                    .map(peliculaSerieMapper::entityToDTO);
        }
    }


    public Page<PeliculaSerieDTO> findByTituloContains(
            String titulo,
            Integer idgenero,
            String order,
            String orderField)
    {
        Pageable pageable= PageRequest.of(0,10,Sort.Direction.valueOf(order),orderField);

        if(Objects.isNull(titulo)||Objects.equals(titulo,""))
        {
            throw new ValidationException("No se ingreso titulo");
        }

        if(Objects.isNull(idgenero))
        {
            return peliculaSerieRepository
                    .findByTituloContains(titulo,pageable)
                    .map(peliculaSerieMapper::entityToDTO);
        }else {
            Page<PeliculaSerie> lista = peliculaSerieRepository.findAll(pageable);

            List<PeliculaSerieDTO> peliculaSerieDTOList = peliculaSerieRepository
                    .findByTituloContains(titulo, pageable)
                    .stream()
                    .filter(peliculaSerieEntity -> peliculaSerieEntity.getGenero().getId().equals(idgenero))
                    .map(peliculaSerieEntity -> peliculaSerieMapper.entityToDTO(peliculaSerieEntity))
                    .collect(Collectors.toList());

            Page<PeliculaSerieDTO> page = new PageImpl<>(peliculaSerieDTOList);
            return page;
        }
    }





    public PeliculaSerieDTO save(PeliculaSerieGuardarDTO dto) {
        if ((Objects.equals(dto.getTitulo(), null))
                ||
                (!Objects.isNull((peliculaSerieRepository.findByTitulo(dto.getTitulo())))))
        {
            throw new ValidationException("Ingreso incorrecto de datos o ya existe");
        } else {
            dto.setId(null);
             return peliculaSerieMapper
                    .entityToDTO(peliculaSerieRepository
                            .save(peliculaSerieMapper.DTOGuardarToEntity(dto)));
        }
    }


    public PeliculaSerieDTO edit(Integer idPeliculaSerie, PeliculaSerieDTO dto) {
        if ((!peliculaSerieRepository.existsById(idPeliculaSerie))) {
            throw new NotFoundException("Pelicula o Serie no encontrada");
        } else {
            dto.setId(idPeliculaSerie);
            return peliculaSerieMapper
                    .entityToDTO(peliculaSerieRepository
                            .save(peliculaSerieMapper.DTOToEntity(dto)));
        }
    }



    public PeliculaSerieDTO delete(Integer idPeliculaSerie) {
        if (!peliculaSerieRepository.existsById(idPeliculaSerie)) {
            throw new NotFoundException("Pelicula o Serie no encontradq");
        } else {
            PeliculaSerie entity = peliculaSerieRepository.getById(idPeliculaSerie);
            peliculaSerieRepository.deleteById(idPeliculaSerie);
            return peliculaSerieMapper.entityToDTO(entity);
        }
    }
    
    
    
    
    
}
