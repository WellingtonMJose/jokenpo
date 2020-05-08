package br.com.wmj.jokenpo.enumeration;

import java.util.Arrays;


public enum EnumException {

    GENERIC_ERROR("ERRO-0001A", "JOKENPO", "GENERIC ERROR", "GENERIC ERROR", "Generic error"),
    INVALID_PARAM("ERRO-0001B", "JOKENPO", "PARAM", "INVALID", "Parametro invalido"),
    
    JOGADOR_NOT_FOUND("ERRO-0002A", "JOKENPO", "JOGADOR", "NOT FOUND", "Jogador não encontrado"),
    JOGADOR_ALREADY_EXISTS("ERRO-0002B", "JOKENPO", "JOGADOR", "ALREADY EXISTS", "Jogador já registrado"),
    JOGADOR_INVALID_NAME("ERRO-0002C", "JOKENPO", "JOGADOR", "NAME", "Nome invalido"),
    JOGADOR_SAVE_ERROR("ERRO-0002D", "JOKENPO", "JOGADOR", "SAVE", "Erro ao salvar jogador"),
    JOGADOR_DELETE_ERROR("ERRO-0002E", "JOKENPO", "JOGADOR", "SAVE", "Erro ao deletar jogador"),
    JOGADOR_FIND_ALL_ERROR("ERRO-0002F", "JOKENPO", "JOGADOR", "FIND ALL", "Erro ao procurar os jogadores"),

    MOVEMENT_NOT_FOUND("ERRO-0003A", "JOKENPO", "MOVEMENT", "NOT FOUND", "Movimento não encontrado"),
    MOVEMENT_ALREADY_EXISTS("ERRO-0003B", "JOKENPO", "MOVEMENT", "ALREADY EXISTS", "Este jogador já jogou antes"),
    MOVEMENT_INVALID("ERRO-0003C", "JOKENPO", "MOVEMENT", "INVALID", "Movimento invalido"),
    MOVEMENT_SAVE_ERROR("ERRO-0003D", "JOKENPO", "MOVEMENT", "SAVE", "Erro ao salvar"),
    MOVEMENT_DELETE_ERROR("ERRO-0003E", "JOKENPO", "MOVEMENT", "SAVE", "Erro ao deletar"),
    MOVEMENT_FIND_ALL_ERROR("ERRO-0003F", "JOKENPO", "MOVEMENT", "FIND ALL", "Erro ao localizar movimento"),

    NOBODY_JOGAR("ERRO-0004A", "JOKENPO", "JOGAR", "NOBODY", "Não há ninguém jogando"),
    INSUFFICIENT_JOGADOR("ERRO-0004B", "JOKENPO", "JOGAR", "INSUFFICIENT JOGADOR", "Número insuficiente de jogadores"),
    INSUFFICIENT_MOVEMENTS("ERRO-0004C", "JOKENPO", "JOGAR", "INSUFFICIENT MOVEMENTS", "Número de movimentos ainda insuficientes"),
    JOGADORS_PENDING("ERRO-0004D", "JOKENPO", "JOGAR", "JOGADORS PENDING", "Existem jogadores que ainda não escolheram");

    private String code;
    private String origin;
    private String type;
    private String subType;
    private String message;

    EnumException(String code, String origin, String type, String subType, String message) {
        this.code = code;
        this.origin = origin;
        this.type = type;
        this.subType = subType;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getOrigin() {
        return origin;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static EnumException getEnumExceptionByCode(String code){
        for (EnumException elem : Arrays.asList(EnumException.values())) {
            if (code.equals(elem.getCode())) {
                return elem;
            }
        }
        return EnumException.GENERIC_ERROR;
    }

}
