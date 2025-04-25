package br.com.fiap.mercadoapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity 
public class Personagem {
 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "O nome é obrigatório")
    private String nome;

    @NotBlank(message= "A classe é obrigatória")
    private Classe classe;

    @Min(value=1, message= "Nível minímo é 1")
    private int nivel;

    @Min(value=0, message= "O saldo não pode ser negativo")
    private double moedas;

    public Personagem() {
    }

    //getter e setter

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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getMoedas() {
        return moedas;
    }

    public void setMoedas(double moedas) {
        this.moedas = moedas;
    }

}
 