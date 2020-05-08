package br.com.wmj.jokenpo.dto;

import br.com.wmj.jokenpo.enumeration.EnumMovement;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MoveResponse {

    @NotNull(message = "Jogador necessário")
    private JogadorResponse jogador;

    @NotNull(message = "Jogada necessária")
    private EnumMovement jogada;

    public MoveResponse() {
    }

    public MoveResponse(JogadorResponse jogador, EnumMovement jogada) {
        this.jogador = jogador;
        this.jogada = jogada;
    }

    public JogadorResponse getJogador() {
        return jogador;
    }

    public void setJogador(JogadorResponse jogador) {
        this.jogador = jogador;
    }

    public EnumMovement getJogada() {
        return jogada;
    }

    public void setJogada(EnumMovement jogada) {
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
        MoveResponse that = (MoveResponse) o;
        return Objects.equals(jogador, that.jogador)
                && jogada == that.jogada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogador, jogada);
    }

    @Override
    public String toString() {
        return "MoveResponse{"
                + "jogador=" + jogador
                + ", jogada=" + jogada
                + '}';
    }

}
