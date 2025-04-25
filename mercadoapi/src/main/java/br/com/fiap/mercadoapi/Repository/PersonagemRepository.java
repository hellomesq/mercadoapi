package br.com.fiap.mercadoapi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.mercadoapi.model.Personagem;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long>{
    List<Personagem> findByUserId(Long userId);

}