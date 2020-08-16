package br.com.casadocodigo.controller;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.casadocodigo.dto.AuthorDto;
import br.com.casadocodigo.form.AuthorForm;
import br.com.casadocodigo.model.Author;
import br.com.casadocodigo.model.Book;


@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	@PersistenceContext
	private EntityManager manager;
	
	public AuthorController(EntityManager manager) {
		this.manager = manager;
	}
	
	@GetMapping
	public List<Author> getAll(){
		
		return  manager
					.createQuery("SELECT a FROM Author a", Author.class)
					.getResultList();					
	}
	
	@GetMapping("{id}")
	public Author getAuthor(@PathVariable(value = "id") Long id){
			
		return  manager.find(Author.class, id);
					
	}
	
	
	@DeleteMapping("{id}")
	@Transactional
	public void delete(@PathVariable(value = "id") Long id){
		
		Author author = manager.find(Author.class, id);
		
		manager.remove(author);					
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AuthorDto> register(@RequestBody @Valid AuthorForm input, UriComponentsBuilder uriBuilder) {
				
		Author author = input.toAuthorType();
		
		manager.persist(author);	
			
		URI uri = uriBuilder				
					.path("/authors/{id}")
					.buildAndExpand(author.getId())
					.toUri();
		
		return ResponseEntity
					.created(uri)
					.body(new AuthorDto(author));
				
	}
	
}
