package br.com.casadocodigo.dto;

import br.com.casadocodigo.model.Country;
import br.com.casadocodigo.model.State;
import lombok.Data;

@Data
public class StateDto {

    private Long id;
    private String name;

    public StateDto(State state) {
        this.id = state.getId();
        this.name = state.getName();
    }

}
