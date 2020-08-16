package br.com.casadocodigo.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.casadocodigo.model.Author;
import lombok.Data;

@Data
public class AuthorDto {
	
	private Long id;
	private String name;
	private String email;
	
	public AuthorDto(Author author) {
		
		this.id = author.getId();
		this.name = author.getName();
		this.email = author.getEmail();
		
	}

}
