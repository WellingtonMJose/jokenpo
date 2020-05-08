package br.com.wmj.jokenpo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class JogadorRequest {

    @NotNull( message = "Nome do jogador necessário" )
    @JsonProperty(value = "nomeJogador")
    private String nome;

    public JogadorRequest(){
    }

    public JogadorRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JogadorRequest that = (JogadorRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "JogadorRequest{" +
                "nome='" + nome + '\'' +
                '}';
    }

}
