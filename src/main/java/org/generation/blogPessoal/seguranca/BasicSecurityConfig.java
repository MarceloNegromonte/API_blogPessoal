package org.generation.blogPessoal.seguranca;
//A camada Basic Security Config é uma uma classe de configuração, é ela que configurar o
//po de criptografia que será ulizada na senha, qual o po de segurança que ulizaremos (no nosso caso o
//basic), e também quais End Point’s que serão liberados para que usuário possa acessar, como por
//exemplo End Point de Logar e Cadastrar.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //declara que é classe de segurança
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	//camada de configuração basica da segurança;
	
	@Autowired //injeção de dependencia
	private UserDetailsService userDetailService;
	
	@Bean //usar o @Bean em um método, e tornar a instância retornada pelo método como um objeto gerenciado pelo Spring (seja de uma classe própria ou de terceiros)
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //validação de login
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	
		auth.userDetailsService(userDetailService);

		auth.inMemoryAuthentication()
		.withUser("root")
		.password(passwordEncoder().encode("root"))
		.authorities("ROLE_USER");

	}
}