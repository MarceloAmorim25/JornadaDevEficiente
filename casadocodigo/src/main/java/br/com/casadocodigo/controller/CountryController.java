package br.com.casadocodigo.controller;

import br.com.casadocodigo.dto.CountryDto;
import br.com.casadocodigo.form.CountryForm;
import br.com.casadocodigo.model.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/countries")
public class CountryController {

    @PersistenceContext
    private EntityManager manager;

    public CountryController(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public List<Country> getAll(){
        return  manager
                .createQuery("SELECT c FROM Country c", Country.class)
                .getResultList();
    }


    @GetMapping("{id}")
    public Country getCountry (@PathVariable(value = "id") Long id){
        return manager.find(Country.class, id);
    }


    @DeleteMapping("{id}")
    @Transactional
    public void delete(@PathVariable(value = "id") Long id){
        Country country = manager.find(Country.class, id);
        manager.remove(country);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<CountryDto> register(@RequestBody @Valid CountryForm input, UriComponentsBuilder uriBuilder) {

        Country country = input.toCountryType();
        manager.persist(country);

        URI uri = uriBuilder
                .path("/authors/{id}")
                .buildAndExpand(country.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new CountryDto(country));

    }

}
