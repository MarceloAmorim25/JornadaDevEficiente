package br.com.casadocodigo.dto;

import br.com.casadocodigo.model.Category;
import br.com.casadocodigo.model.Country;

public class CountryDto {

    private Long id;
    private String name;

    public CountryDto(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }

}
