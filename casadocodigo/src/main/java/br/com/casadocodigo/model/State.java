package br.com.casadocodigo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class State {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(optional = false)
    private Country country;

    public State() { };

    public State(String name) {
        this.name = name;
    }

}
