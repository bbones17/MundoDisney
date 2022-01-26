package com.disney.mundo.Model.Repositories;

import com.disney.mundo.Model.DTO.PeliculaSerieDTO;
import com.disney.mundo.Model.DTO.PersonajeDTO;
import com.disney.mundo.Model.Entities.Genero;
import com.disney.mundo.Model.Entities.PeliculaSerie;
import com.disney.mundo.Model.Entities.Personaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface  PeliculaSerieRepository extends PagingAndSortingRepository<PeliculaSerie,Integer> {

    Page<PeliculaSerie> findAll(Pageable pageable);
    Page<PeliculaSerie> findById(Integer id, Pageable pageable);
    PeliculaSerie getById(Integer idPeliculaSerie);
    Page<PeliculaSerie> findByTituloContains(String titulo, Pageable pageable);

    PeliculaSerie save(PeliculaSerie entity);

    Optional<PeliculaSerie> findById(Integer id);

   PeliculaSerie findByTitulo(String titulo);

}
