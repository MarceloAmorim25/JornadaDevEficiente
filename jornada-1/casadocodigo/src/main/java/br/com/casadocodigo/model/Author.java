package br.com.casadocodigo.model;

import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Author {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	public Author(@NotNull String name, @Email @NotNull @NotEmpty String email) {
		super();
		this.name = name;
		this.email = email;
	}

	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	@Size(max=400)
	private String description;
	
	private LocalDateTime registeredAt = LocalDateTime.now();
		
	public Author() {};

}
