package org.generation.blogPessoal.seguranca;
//User Details é uma camada que contem todos os métodos que podemos chamar no User Details Service
//e em outras camadas.
import java.util.Collection;
import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//DTO
public class UserDetailsImpl implements UserDetails {
	//classe user details implementação    | // vamos implementar uma regra de negocio que já existe, da interface userDetails.

	private static final Long serialVersionUID = 1L; //faz a classe ser apenas para controle interno // serial de identificação 
	
	private String userName; //usuario identificador
	private String password;
	private List<GrantedAuthority> authorities; //diz que pode passar algumas coisas dentro desse metodo
	
	public UserDetailsImpl(Usuario user) { // construtor de classe, recebendo como parametro o User com login e senha
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl() { //construtor vazio
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
