package br.com.wmj.jokenpo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class MoveRequest {

    @Size(min = 1)
    @NotNull(message = "Nome do jogador obrigatório")
    private String nomeJogador;

    @Size(min = 1)
    @NotNull(message = "Jogada obrigatória")
    private String jogada;

    public MoveRequest() {
    }

    public MoveRequest(String nomeJogador, String jogada) {
        this.nomeJogador = nomeJogador;
        this.jogada = jogada;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public String getJogada() {
        return jogada;
    }

    public void setJogada(String jogada) {
        this.jogada = jogada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoveRequest that = (MoveRequest) o;
        return Objects.equals(nomeJogador, that.nomeJogador)
                && Objects.equals(jogada, that.jogada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeJogador, jogada);
    }

    @Override
    public String toString() {
        return "MoveRequest{"
                + "nomeJogador='" + nomeJogador + '\''
                + ", jogada='" + jogada + '\''
                + '}';
    }

}
