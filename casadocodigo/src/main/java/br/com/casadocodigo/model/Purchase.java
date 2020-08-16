package br.com.casadocodigo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String lastname;
    private Double document;
    private String address;
    private String complement;
    private String city;
    private Country country;
    private State state;
    private String telephone;
    private String cep;

}
