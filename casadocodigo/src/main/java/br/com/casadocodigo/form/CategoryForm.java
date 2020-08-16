package br.com.casadocodigo.form;

import br.com.casadocodigo.model.Category;
import lombok.Data;

@Data
public class CategoryForm {
	
	private String name;
	
	public Category toCategoryType() {
		return new Category(name);	
	}

}
