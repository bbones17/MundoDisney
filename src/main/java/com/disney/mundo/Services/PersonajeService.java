package com.disney.mundo.Services;

import com.disney.mundo.Exceptions.NotFoundException;
import com.disney.mundo.Exceptions.ValidationException;
import com.disney.mundo.Model.DTO.PersonajeDTO;
import com.disney.mundo.Model.DTO.PersonajeGuardarDTO;
import com.disney.mundo.Model.DTO.PersonajeListadoDTO;
import com.disney.mundo.Model.Entities.Personaje;
import com.disney.mundo.Model.Mappers.PersonajeListadoMapper;
import com.disney.mundo.Model.Mappers.PersonajeMapper;
import com.disney.mundo.Model.Repositories.PersonajeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
//busqueda personajes

@Service("personajeService")
public class PersonajeService  {

    private final PersonajeRepository personajeRepository;
    private final PersonajeMapper personajeMapper;
    private final PersonajeListadoMapper personajeListadoMapper;

    public PersonajeService(PersonajeRepository personajeRepository, PersonajeMapper personajeMapper, PersonajeListadoMapper personajeListadoMapper)
    {
        this.personajeRepository=personajeRepository;
        this.personajeMapper=personajeMapper;
        this.personajeListadoMapper=personajeListadoMapper;
    }


    //Lista el personaje con todos sus datos
    public Page<PersonajeDTO> findAll(Integer pageNumber, Integer pageSize, String orderField) {
        if (Objects.equals(orderField, null)) {
            Pageable pageable = PageRequest.of(0, 10);
            return personajeRepository.findAll(pageable).map(personajeMapper::entityToDTO);

        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orderField));
            return personajeRepository.findAll(pageable).map(personajeMapper::entityToDTO);
        }
    }


    //Lista el personaje solo con su imagen y nombre
    public Page<PersonajeListadoDTO> findAllListado(Integer pageNumber, Integer pageSize, String orderField) {
        if (Objects.equals(orderField, null)) {
            Pageable pageable = PageRequest.of(0, 10);
            return personajeRepository.findAll(pageable).map(personajeListadoMapper::entityToDTO);

        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(orderField));
            return personajeRepository.findAll(pageable).map(personajeListadoMapper::entityToDTO);
        }
    }



    public Page<PersonajeDTO> findById(Integer id, Integer pageNumber, Integer pageSize, String orderField) {
        if (!personajeRepository.existsById(id)) {
            throw new NotFoundException("Personaje no encontrado");
        } else {
            PersonajeDTO dto = personajeMapper.entityToDTO(personajeRepository.findById(id).get());
            Pageable pageable= PageRequest.of(0,1);
            return personajeRepository.findById(dto.getId(),pageable).map(personajeMapper::entityToDTO);
        }
    }


    public PersonajeDTO save( PersonajeGuardarDTO dto) {
        if ((Objects.equals(dto.getNombre(), null))
                ||
                (!Objects.isNull((personajeRepository.findByNombre(dto.getNombre())))))
        {
            throw new ValidationException("Error en los datos o ya existe");
        } else {
            dto.setId(null);
            return personajeMapper
                    .entityToDTO(personajeRepository
                            .save(personajeMapper.DTOGuardarToEntity(dto)));
        }
    }

    public PersonajeDTO edit(Integer idPersonaje, PersonajeGuardarDTO dto) {
        if ((!personajeRepository.existsById(idPersonaje))) {
            throw new NotFoundException("Personaje no encontrado");
        } else {
            dto.setId(idPersonaje);
            return personajeMapper.entityToDTO(personajeRepository.save(personajeMapper.DTOGuardarToEntity(dto)));
        }
    }


    public PersonajeListadoDTO delete(Integer idPersonaje) {
        if (!personajeRepository.existsById(idPersonaje)) {
            throw new NotFoundException("Personaje no encontrado");
        } else {
            Personaje entity = personajeRepository.getById(idPersonaje);
            personajeRepository.deleteById(idPersonaje);
            return personajeListadoMapper.entityToDTO(entity);
        }
    }


    public Page<PersonajeDTO> findByNombreContains(
            String nombre,
            Integer edad,
            Double peso,
            String order,
            String orderField)
    {
        Pageable pageable= PageRequest.of(0,10,Sort.Direction.valueOf(order),orderField);
        if(Objects.isNull(nombre)||Objects.equals(nombre,""))
        {
            throw new ValidationException("Nombre incorrecto o no ingresado");
        }

        if(Objects.isNull(edad)&&Objects.isNull(peso)) {
            return personajeRepository
                    .findByNombreContains(nombre, pageable)
                    .map(personajeMapper::entityToDTO);
        }
        else
        {  List<PersonajeDTO> personajeDTOList=   personajeRepository
                    .findByNombreContains(nombre, pageable)
                    .stream()
                    .filter(personaje -> personaje.getEdad().equals(edad) || personaje.getPeso().equals(peso))
                    .map(personaje -> personajeMapper.entityToDTO(personaje))
                    .collect(Collectors.toList());

            Page<PersonajeDTO> page = new PageImpl<>(personajeDTOList);
            return page;
        }

    }

}
