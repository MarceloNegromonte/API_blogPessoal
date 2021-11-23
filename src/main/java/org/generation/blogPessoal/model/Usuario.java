package org.generation.blogPessoal.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 100)
	private String nome;

	//@ApiModelProperty(example = "email@email.com.br") /não quer ler no meu Eclipse
	@NotNull(message = "O atributo Usuario é Obrigatrio!")
	@Email(message = "O Atributo Usuario deve ser um email válido!")
	private String usuario;

	@NotNull
	@Size(min = 5, max = 15)
	private String senha;
	
	@Column(name = "dt_nascimento")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dataNascimento;
				
	public Usuario(Long id, @NotNull @Size(min = 2, max = 100) String nome,
			@NotNull @Size(min = 5, max = 100) String usuario, @NotNull @Size(min = 5, max = 15) String senha,
			LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public Usuario() { 
	}

	@OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"criador"})
	private List<Postagem> minhasPostagens = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Postagem> getMinhasPostagens() {
		return minhasPostagens;
	}

	public void setMinhasPostagens(List<Postagem> minhasPostagens) {
		this.minhasPostagens = minhasPostagens;
	}

	public Object getFoto() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
