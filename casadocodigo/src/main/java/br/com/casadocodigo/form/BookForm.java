package br.com.casadocodigo.form;

import br.com.casadocodigo.model.Book;
import lombok.Data;

@Data
public class BookForm {

	private String title;
	private Double price;
	private Double pages;
	
	public BookForm() {}
	
	public BookForm(String title, Double price, Double pages) {
		super();
		this.title = title;
		this.price = price;
		this.pages = pages;
	}
	
	public Book toBookType() {		
		return new Book(title, price, pages);		
	}

}
