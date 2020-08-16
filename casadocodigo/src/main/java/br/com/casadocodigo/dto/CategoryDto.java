package br.com.casadocodigo.dto;

import br.com.casadocodigo.model.Category;
import lombok.Data;

@Data
public class CategoryDto {

	private Long id;
	private String name;
	
	public CategoryDto(Category category) {		
		this.id = category.getId();
		this.name = category.getName();		
	}
}
