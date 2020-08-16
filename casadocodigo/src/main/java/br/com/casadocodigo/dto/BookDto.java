package br.com.casadocodigo.dto;

import br.com.casadocodigo.model.Book;
import lombok.Data;

@Data
public class BookDto {
	
	private Long id;
	private String title;
	private Double price;
	private Double pages;
	
	public BookDto(Book book) {
		this.id = getId();
		this.title = getTitle();
		this.price = getPrice();
		this.pages = getPages();
	}

}
