package br.com.casadocodigo.controller;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.casadocodigo.dto.BookDto;
import br.com.casadocodigo.form.BookForm;
import br.com.casadocodigo.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@PersistenceContext
	private EntityManager manager;
	
	public BookController(EntityManager manager) {
		this.manager = manager;
	}
	
	@GetMapping
	public List<Book> getAll(){
		
		return  manager
				.createQuery("SELECT b FROM Book b", Book.class)
				.getResultList();
	}
	
	
	@GetMapping("{id}")
	public Book getBook(@PathVariable(value = "id") Long id){
			
		return  manager.find(Book.class, id);
					
	}
	
	
	@DeleteMapping("{id}")
	@Transactional
	public void delete(@PathVariable(value = "id") Long id){
		
		Book book = manager.find(Book.class, id);
		
		manager.remove(book);					
	}
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<BookDto> create(@RequestBody @Valid BookForm input, UriComponentsBuilder uriBuilder) {
				
		Book book = input.toBookType();
		
		manager.persist(book);	
			
		URI uri = uriBuilder
				.path("/books/{id}")
				.buildAndExpand(book.getId())
				.toUri();
		
		return ResponseEntity
				.created(uri)
				.body(new BookDto(book));
				
	}

}
