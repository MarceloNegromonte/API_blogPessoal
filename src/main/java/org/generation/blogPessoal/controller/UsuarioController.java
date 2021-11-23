package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
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

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*") //2 parametros, as origins todas e alloheadss é pra dizer que dentro do hears iremos aceitas qualquer informação
public class UsuarioController {

	
	@Autowired
	private UsuarioService usuarioService; //diferente das outas, essa classe injeta o usuario servico e não repository

	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity <List<Usuario>> getAll() {
	        return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id) {
	        return repository.findById(id)
		        .map(resp -> ResponseEntity.ok(resp))
		        .orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){		
	        return usuarioService.atualizarUsuario(usuario)
	                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
	                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PostMapping("/logar")
	public ResponseEntity<UserLogin> autentication(@Valid @RequestBody Optional <UserLogin> user){
	        return usuarioService.logarUsuario(user).map(resp -> ResponseEntity.ok(resp))
			        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> post(@Valid @RequestBody Usuario usuario){
	        return ResponseEntity.status(HttpStatus.CREATED)
			        .body(usuarioService.cadastrarUsuario(usuario));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
	        Optional<Usuario> tema = repository.findById(id);
	        if(tema.isEmpty())
		        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        repository.deleteById(id);				

	}
}
