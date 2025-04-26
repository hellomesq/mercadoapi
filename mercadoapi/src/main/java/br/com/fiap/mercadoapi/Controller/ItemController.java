package br.com.fiap.mercadoapi.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.mercadoapi.Repository.ItemRepository;
import br.com.fiap.mercadoapi.model.Item;
import br.com.fiap.mercadoapi.specification.ItemSpecification;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ItemRepository repository;

    // Método para listar itens com filtros
    @GetMapping
    public List<Item> listarItens(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Double minPreco,
            @RequestParam(required = false) Double maxPreco,
            @RequestParam(required = false) String raridade
    ) {
        var spec = ItemSpecification.withFilters(nome, tipo, minPreco, maxPreco, raridade);
        return repository.findAll(spec);
    }

    // Método para listar todos os itens (sem filtros)
    @GetMapping("/todos")
    public List<Item> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Item criar(@RequestBody @Valid Item item) {
        log.info("Cadastrando item: " + item.getNome());
        return repository.save(item);
    }

    @GetMapping("{id}")
    public Item buscar(@PathVariable Long id) {
        log.info("Buscando item " + id);
        return getItem(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        log.info("Deletando item " + id);
        repository.delete(getItem(id));
    }

    @PutMapping("{id}")
    public Item atualizar(@PathVariable Long id, @RequestBody @Valid Item item) {
        log.info("Atualizando item " + id + " para " + item.getNome());
        item.setId(id);
        return repository.save(item);
    }

    private Item getItem(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }
}
