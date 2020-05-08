package br.com.wmj.jokenpo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class JogadorResponse {

    @NotNull
    @JsonProperty(value = "nomeJogador")
    private String nome;

    public JogadorResponse(){
    }

    public JogadorResponse(String nome) {
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
        JogadorResponse that = (JogadorResponse) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "JogadorResponse{" +
                "nome='" + nome + '\'' +
                '}';
    }

}
