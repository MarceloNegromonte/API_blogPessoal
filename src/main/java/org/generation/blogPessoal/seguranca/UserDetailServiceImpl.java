	package org.generation.blogPessoal.seguranca;
////User Details Service Camada responsável diretamente para validação da senha e criação do Basic token.
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //falar que trata de um serviço
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired //injeção da dependencia
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException { // metodo que vai devolver um USERDETAILS
		Optional<Usuario> user = userRepository.findByUsuario(userName); //recebe um Usuario(classe) o que entra no objeto é o repositorio.findbyusuario e passamos o username
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found.")); //caso de erro chamamos a tratativa
	
		return user.map(UserDetailsImpl::new).get(); //vai entrar um novo userdetails
	}
}
