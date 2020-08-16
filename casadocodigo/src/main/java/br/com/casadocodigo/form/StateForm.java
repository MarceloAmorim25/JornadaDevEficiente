package br.com.casadocodigo.form;

import br.com.casadocodigo.model.Country;
import br.com.casadocodigo.model.State;

public class StateForm {

    private String name;

    public State toStateType() {
        return new State(name);
    }

}
