package br.com.casadocodigo.model;

import javax.persistence.*;

public class State {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(optional = false)
    private Country country;

}
