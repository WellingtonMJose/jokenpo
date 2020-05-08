package br.com.wmj.jokenpo.entity;

import br.com.wmj.jokenpo.enumeration.EnumMovement;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class MoveEntity {

    private JogadorEntity jogador;
    private EnumMovement enumMovement;

    public MoveEntity() {
    }

    public MoveEntity(JogadorEntity jogador, EnumMovement enumMovement) {
        this.jogador = jogador;
        this.enumMovement = enumMovement;
    }

    public JogadorEntity getJogador() {
        return jogador;
    }

    public void setJogador(JogadorEntity jogador) {
        this.jogador = jogador;
    }

    

    public EnumMovement getEnumMovement() {
        return enumMovement;
    }

    public void setEnumMovement(EnumMovement enumMovement) {
        this.enumMovement = enumMovement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MoveEntity that = (MoveEntity) o;
        return Objects.equals(jogador, that.jogador)
                && Objects.equals(enumMovement, that.enumMovement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jogador, enumMovement);
    }

    @Override
    public String toString() {
        return "MoveEntity{"
                + "jogador=" + jogador
                + ", enumMovement=" + enumMovement
                + '}';
    }

}
