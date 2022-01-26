package com.disney.mundo.Model.Entities;

import com.disney.mundo.Model.DTO.PeliculaSerieDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "genero")
public class Genero {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgenero", nullable = false)
   private Integer id;
    @Column(name = "imagen", columnDefinition = "VARCHAR")
    String imagen;
    @Column(name = "nombre", columnDefinition = "VARCHAR")
    String nombre;

    @JsonIgnore
    @OneToMany
    @JoinTable(
            name = "peliculasdelgenero",
            joinColumns = @JoinColumn(name = "idgenero"),
            inverseJoinColumns = @JoinColumn(name = "idpeliculaserie")
    )
    List<PeliculaSerie> peliculasDelGenero;


    @Override
    public String toString()
    {
        return PeliculaSerie.class.toString();
    }

}
