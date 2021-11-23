package org.generation.blogPessoal.model;
//O Controller que rebeberá o Usurio com seu login e sua senha, e devolverá o recurso com a
//Authorization Basic + token.
public class UserLogin {

	//criada sem anotation
	private long id;
	private String nome;
	private String usuario;
	private String foto;
	private String senha;
	private String token;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(Object object) {
		this.foto = (String) object;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
