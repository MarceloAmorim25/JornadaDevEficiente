package br.com.casadocodigo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<State> state = new HashSet<State>();

    public Country() { };

    public Country(String name) {
        this.name = name;
    }

}
