package br.com.wmj.jokenpo.enumeration;

import br.com.wmj.jokenpo.exception.JokenpoException;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public enum EnumMovement {

   
    TESOURA("TESOURA"),
    PAPEL("PAPEL"),
    PEDRA("PEDRA");
 

    private String nome;
    private List<EnumMovement> fraqueza;

    static {
       
        TESOURA.setFraqueza(asList(PEDRA));
        PAPEL.setFraqueza(asList(TESOURA));
        PEDRA.setFraqueza(asList( PAPEL));
        
    }

    EnumMovement(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<EnumMovement> getFraqueza() {
        return fraqueza;
    }

    public void setFraqueza(List<EnumMovement> fraqueza) {
        this.fraqueza = fraqueza;
    }

    public static EnumMovement getEnumMovementByName(String nome) throws JokenpoException {
        for (EnumMovement elem : Arrays.asList(EnumMovement.values())) {
            if (nome.equals(elem.getNome())) {
                return elem;
            }
        }
        throw new JokenpoException(EnumException.MOVEMENT_NOT_FOUND);
    }

}
