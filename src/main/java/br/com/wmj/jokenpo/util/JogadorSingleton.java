package br.com.wmj.jokenpo.util;

import br.com.wmj.jokenpo.entity.JogadorEntity;

import java.util.ArrayList;
import java.util.List;

public final class JogadorSingleton {

    private static List<JogadorEntity> PLAYER_INSTANCE;
    private static String INFO = "Player Singleton Instance";

    private JogadorSingleton(){
    }

    public static List<JogadorEntity> getInstance() {
        if(PLAYER_INSTANCE == null) {
            PLAYER_INSTANCE = new ArrayList<JogadorEntity>();
        }
        return PLAYER_INSTANCE;
    }

    public static List<JogadorEntity> clear(){
        PLAYER_INSTANCE = new ArrayList<JogadorEntity>();
        return getInstance();
    }

    public String getInfo() {
        return this.INFO;
    }

}
