package br.com.casadocodigo.model;
import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Book {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;
	private String description;
	private String summary;
	private Double price;
	private Double pages;
	private Double isbn;
	private LocalDateTime published_at;
	
	public Book() {}
	
	public Book(@NotNull String title, Double price, Double pages) {
		this.price = price;
		this.title = title;
		this.pages = pages;
	}

}
