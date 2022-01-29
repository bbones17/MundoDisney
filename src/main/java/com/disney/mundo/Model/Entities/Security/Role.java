package com.disney.mundo.Model.Entities.Security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="roleid")
     Integer roleid;

    @Column(name="name")
     String name;
}