package br.com.fiap.mercadoapi.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.mercadoapi.Repository.PersonagemRepository;
import br.com.fiap.mercadoapi.model.Personagem;
import br.com.fiap.mercadoapi.specification.PersonagemSpecification;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    public List<Personagem> listarPersonagens(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String classe
    ) {
        var spec = PersonagemSpecification.withFilters(nome, classe);
        return repository.findAll(spec);
    }

    @GetMapping("/todos")
    public List<Personagem> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Personagem criar(@RequestBody @Valid Personagem personagem) {
        log.info("Cadastrando personagem: " + personagem.getNome());
        return repository.save(personagem);
    }

    @GetMapping("{id}")
    public Personagem buscar(@PathVariable Long id) {
        log.info("Buscando personagem " + id);
        return getPersonagem(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        log.info("Deletando personagem " + id);
        repository.delete(getPersonagem(id));
    }

    @PutMapping("{id}")
    public Personagem atualizar(@PathVariable Long id, @RequestBody @Valid Personagem personagem) {
        log.info("Atualizando personagem " + id + " para " + personagem.getNome());
        personagem.setId(id);
        return repository.save(personagem);
    }

    private Personagem getPersonagem(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
    }
}
