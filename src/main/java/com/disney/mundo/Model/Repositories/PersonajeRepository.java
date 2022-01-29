package com.disney.mundo.Model.Repositories;

import com.disney.mundo.Model.Entities.Personaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PersonajeRepository  extends PagingAndSortingRepository<Personaje,Integer> {

        Page<Personaje> findAll();
        Page<Personaje> findById(Integer id, Pageable pageable);
        Personaje getById(Integer id);
        Page<Personaje> findByNombreContains(String nombre, Pageable pageable);
        Optional<Personaje> findById(Integer idpersonaje);
        Personaje findByNombre(String nombre);
}
