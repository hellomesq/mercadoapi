package br.com.fiap.mercadoapi.specification;

import org.springframework.data.jpa.domain.Specification;
import br.com.fiap.mercadoapi.model.Personagem;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class PersonagemSpecification {

    public static Specification<Personagem> withFilters(String nome, String classe) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por nome
            if (nome != null && !nome.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            // Filtro por classe
            if (classe != null && !classe.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("classe")), classe.toLowerCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
