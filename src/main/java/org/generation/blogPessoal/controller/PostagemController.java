package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController //informa para o String que trata de uma classe controladora
@RequestMapping("/postagem") //informa qual a URI a classe será acessada
@CrossOrigin(value = "*", allowedHeaders = "*") //informar que a classe vai aceitar requisições de qualquer origem
public class PostagemController {

	@Autowired //garante que todos os serviços da inteface postagem repository, seja acessado a partir do controller
	private PostagemRepository repository; //injetando a classe repositorio dentro do controller e da o nome de repository

	@GetMapping //sempre que vir uma requisição externa, atraves da uri postagem, se for o metodo get, dispara esse metodo
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	//assim que for feita alguma requisição do tipo get em postagem/o valor id, sera acessado esse metodo, onde o metodo captura a variavel recebendo dentro do pathvariable e retorna a interface injetada com o @Autowired, o metodo findById devolve tanto um objeto do tipo postagem com ok e o objeto dentro do corpo da requisição, caso aja erro na requição retorna um notfound 
	@GetMapping("/{id}") //para busca por id, primeiro determinar o metodo, nesse caso get,e coloca qual o parametro que sera enviado para quem ta fazendo a requisição
	public ResponseEntity<Postagem> GetById(@PathVariable Long id){ //metodo public, tipo response entity, retornando 1 objeto do tipo postagem, nome opcional do metodo, GETBYID
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	//para find(busca)pelo titulo
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
	        return ResponseEntity.status(HttpStatus.CREATED)
			        .body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
	        return repository.findById(postagem.getId())
			        .map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem)))
			        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
	        Optional<Postagem> post = repository.findById(id);
	        if(post.isEmpty())
		        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        repository.deleteById(id);				

	}
	
}
