package com.disney.mundo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "personaje")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersonaje", nullable = false)
     Integer id;

    @Column(name = "imagen", columnDefinition = "VARCHAR")
    String imagen;
    @Column(name = "nombre", columnDefinition = "VARCHAR")
    String nombre;
    @Column(name = "edad", columnDefinition = "INT")
    Integer edad;
    @Column(name = "peso", columnDefinition = "DOUBLE")
    Double peso;
    @Column(name = "historia", columnDefinition = "VARCHAR")
    String historia;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "peliculapersonaje",
            joinColumns = @JoinColumn(name = "idpersonaje"),
            inverseJoinColumns = @JoinColumn(name = "idpeliculaserie")
    )
    List<PeliculaSerie> peliculaseries;



//    @Override
//    public String toString()
//    {
//        return Personaje.class.toString();
//    }



}
