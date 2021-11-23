package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //indica para o spring que a classe se trata de repositorio
public interface PostagemRepository extends JpaRepository<Postagem, Long> { //criando extensão com reposytorio da postagem e tipo long
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo); //o metodo busca todos pelo titulo(nome do atributo da entidade) Containing(tudo que conter dentro da viariavel)(ignorecase = não levar em consideração maiusculo ou minusculo)
}
