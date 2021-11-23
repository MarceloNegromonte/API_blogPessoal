package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	private Usuario usuarioAdmin;

	/*
	@BeforeAll
	public void start(){
		
		LocalDate dataAdmin = LocalDate.parse("1990-07-22",
		DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Usuario usuarioAdmin = new Usuario(0L, "Administrador",
		"admin@email.com.br", "admin123", dataAdmin);

		if(!UsuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {	
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);
		testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
		}
				
		LocalDate dataPost = LocalDate.parse("2000-07-22",
        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Usuario usuario = new Usuario(0L, "Paulo Antunes",
		"paulo@email.com.br", "13465278", dataPost);
	
		LocalDate dataPut = LocalDate.parse("2000-07-22",
		DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		Usuario usuarioUpdate = new Usuario(2L, "Paulo Antunes de Souza",
		"paulo_souza@email.com.br", "souza123", dataPut);
	
	}
		*/
	@Test
	@Order(2)
	@DisplayName("👍 Listar todos os Usuários!")
	public void deveMostrarTodosUsuarios() {

		ResponseEntity<String> resposta = testRestTemplate
		.withBasicAuth("admin@email.com.br", "admin123")
		.exchange("/usuarios/all", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@Order(3)
	@DisplayName("😳 Alterar Usuário!")
	public void deveRealizarPutUsuario() {
	Usuario usuarioUpdate = null;
	HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioUpdate);
	ResponseEntity<Usuario> resposta = testRestTemplate
	.withBasicAuth("admin@email.com.br", "admin123")
	.exchange("/usuarios/alterar", HttpMethod.PUT, request, Usuario.class);
	assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
