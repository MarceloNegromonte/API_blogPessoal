package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull; //ajustar
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // significa que essa classe vai ser uma entidade do jpa reberente ou referente.
@Table(name = "postagem") // atraves dessa anotação que essa entidade dentro do bancos de dados vai ser// uma tabela
public class Postagem {

	// atributos
	// @Id //o atributo se trata de um ID.
	// @GeneratedValue(strategy = GenerationType.IDENTITY) //valor será gerado com a
	// estrategia do tipo identidade. no banco de dados o atribudo vai se
	// transformar em uma chave primaria.
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	@NotNull // não pode ser null
	@Size(min = 5, max = 100) // minimo 5, max 100 caracteres
	private String titulo;

	@NotNull
	@Size(min = 10, max = 500) // minimo 5, max 500 caractere
	private String texto;

	@Temporal(TemporalType.TIMESTAMP) // Indica que ta trabalhando com o tempo e o parametro
	private Date date = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({"minhasPostagens"})
	private Usuario criador;
	
	@ManyToOne
	@JoinColumn(name = "tema_id")
	@JsonIgnoreProperties({"postagem"})
	private Tema tema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
		
}