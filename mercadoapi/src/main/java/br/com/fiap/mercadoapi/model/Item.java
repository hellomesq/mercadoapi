package br.com.fiap.mercadoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do item é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo do item é obrigatório")
    private TipoItem tipo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A raridade é obrigatória")
    private Raridade raridade;

    @Min(value = 0, message = "O preço não pode ser negativo")
    private double preco;

    @ManyToOne
    private Personagem dono;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Personagem getDono() {
        return dono;
    }

    public void setDono(Personagem dono) {
        this.dono = dono;
    }
}
