package br.com.casadocodigo.form;

import br.com.casadocodigo.model.Category;
import br.com.casadocodigo.model.Country;
import lombok.Data;

@Data
public class CountryForm {

    private String name;

    public Country toCountryType() {
        return new Country(name);
    }

}
