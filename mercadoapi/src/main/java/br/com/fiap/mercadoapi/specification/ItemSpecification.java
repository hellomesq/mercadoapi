package br.com.fiap.mercadoapi.specification;

import org.springframework.data.jpa.domain.Specification;
import br.com.fiap.mercadoapi.model.Item;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class ItemSpecification {

    public static Specification<Item> withFilters(String nome, String tipo, Double minPreco, Double maxPreco,
            String raridade) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por nome (parcial)
            if (nome != null && !nome.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            // Filtro por tipo
            if (tipo != null && !tipo.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("tipo")), tipo.toLowerCase()));
            }

            // Filtro por preço mínimo e máximo
            if (minPreco != null && maxPreco != null) {
                predicates.add(cb.between(root.get("preco"), minPreco, maxPreco));
            }

            // Filtro por raridade
            if (raridade != null && !raridade.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("raridade")), raridade.toLowerCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
