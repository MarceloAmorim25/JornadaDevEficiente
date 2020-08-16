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

import br.com.casadocodigo.dto.CategoryDto;
import br.com.casadocodigo.form.CategoryForm;
import br.com.casadocodigo.model.Author;
import br.com.casadocodigo.model.Category;


@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@PersistenceContext
	private EntityManager manager;
	
	public CategoryController(EntityManager manager) {
		this.manager = manager;
	}
	
	@GetMapping
	public List<Category> getAll(){		
		return  manager
					.createQuery("SELECT c FROM Category c", Category.class)
					.getResultList();					
	}
	
	
	@GetMapping("{id}")
	public Category getCategory (@PathVariable(value = "id") Long id){		
		 return manager.find(Category.class, id);				
	}
	
	
	@DeleteMapping("{id}")
	@Transactional
	public void delete(@PathVariable(value = "id") Long id){
		Category category = manager.find(Category.class, id);	
		manager.remove(category);					
	}
	

	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDto> register(@RequestBody @Valid CategoryForm input, UriComponentsBuilder uriBuilder) {
				
		Category category = input.toCategoryType();	
		manager.persist(category);	
			
		URI uri = uriBuilder				
					.path("/authors/{id}")
					.buildAndExpand(category.getId())
					.toUri();
		
		return ResponseEntity
					.created(uri)
					.body(new CategoryDto(category));
				
	}

}
