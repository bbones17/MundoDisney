package com.disney.mundo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "pelicula_serie")
public class PeliculaSerie {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpeliculaserie", nullable = false)
    private Integer id;
    @Column(name = "imagen", columnDefinition = "VARCHAR")
    String imagen;
    @Column(name = "titulo", columnDefinition = "VARCHAR")
    String titulo;
    @Column(name = "fecha_creacion", columnDefinition = "DATE")
    Date fecha_creacion;
    @Column(name = "calificacion", columnDefinition = "INT")
    Integer calificacion;




    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "peliculasdelgenero",
            joinColumns = @JoinColumn(name = "idpeliculaserie"),
            inverseJoinColumns = @JoinColumn(name = "idgenero")
    )
    Genero genero;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "peliculapersonaje",
            joinColumns = @JoinColumn(name = "idpeliculaserie"),
            inverseJoinColumns = @JoinColumn(name = "idpersonaje")
    )
    List<Personaje> personajes;



//    @Override
//    public String toString()
//    {
//        return PeliculaSerie.class.toString();
//    }


}
