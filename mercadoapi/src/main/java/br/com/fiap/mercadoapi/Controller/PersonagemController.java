package br.com.fiap.mercadoapi.Controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.mercadoapi.Repository.PersonagemRepository;
import br.com.fiap.mercadoapi.model.Personagem;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemRepository personagemRepository;

    @GetMapping
    public List<Personagem>listarTodos(){
        return personagemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> get(@PathVariable Long id) {
        return personagemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personagem>create(@Valid @RequestBody Personagem personagem ){
        //log.info("Cadastrando personagem" + personagem.getNome());
        personagemRepository.save(personagem);
        return ResponseEntity.status(201).body(personagem);
    } 

   
}
