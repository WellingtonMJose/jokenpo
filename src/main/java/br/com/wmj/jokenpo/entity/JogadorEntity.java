package br.com.wmj.jokenpo.entity;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class JogadorEntity {

    private String nome;

    public JogadorEntity() {
    }

    public JogadorEntity(String nome) {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        JogadorEntity that = (JogadorEntity) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome);
    }

    @Override
    public String toString() {
        return "JogadorEntity{"
                + "nome=" + nome + '\''
                + '}';
    }

}
