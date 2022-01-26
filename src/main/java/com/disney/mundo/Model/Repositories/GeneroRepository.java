package com.disney.mundo.Model.Repositories;

import com.disney.mundo.Model.Entities.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface GeneroRepository extends PagingAndSortingRepository<Genero,Integer> {

    Page<Genero> findAll(Pageable pageable);
    Page<Genero> findById(Integer id, Pageable pageable);
    Genero getById(Integer idGenero);
}
