package br.com.casadocodigo.form;

import br.com.casadocodigo.model.Author;
import lombok.Data;

@Data
public class AuthorForm {
	
	private String name;
	
	private String email;
	
	public Author toAuthorType() {		
		return new Author(name, email);		
	}
	
	public AuthorForm() {};
	
	public AuthorForm(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}


}
