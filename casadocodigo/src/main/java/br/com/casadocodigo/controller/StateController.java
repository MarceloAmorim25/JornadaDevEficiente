package br.com.casadocodigo.controller;

import br.com.casadocodigo.dto.StateDto;
import br.com.casadocodigo.form.StateForm;
import br.com.casadocodigo.model.Category;
import br.com.casadocodigo.model.State;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public class StateController {

    @PersistenceContext
    private EntityManager manager;

    public StateController(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public List<State> getAll(){
        return  manager
                .createQuery("SELECT s FROM State s", State.class)
                .getResultList();
    }


    @GetMapping("{id}")
    public State getCategory (@PathVariable(value = "id") Long id){
        return manager.find(State.class, id);
    }


    @DeleteMapping("{id}")
    @Transactional
    public void delete(@PathVariable(value = "id") Long id){
        Category category = manager.find(Category.class, id);
        manager.remove(category);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<StateDto> register(@RequestBody @Valid StateForm input, UriComponentsBuilder uriBuilder) {

        State state = input.toStateType();
        manager.persist(state);

        URI uri = uriBuilder
                .path("/authors/{id}")
                .buildAndExpand(state.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new StateDto(state));

    }

}
