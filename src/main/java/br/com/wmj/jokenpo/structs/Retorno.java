package br.com.wmj.jokenpo.structs;

public class Retorno {
    private Status status;
    private String descricao;
    private Object retorno;

    public Retorno ok(){
        setStatus(Status.OK);
        return this;
    }
    public Retorno naoExiste(){
        setStatus(Status.ERRO_REGISTRO_NAO_EXISTE);
        return this;
    }

    public Retorno errorAuth(){
        setStatus(Status.ERRO_AUTH);
        return this;
    }

    public Retorno ex(Exception ex){
        setStatus(Status.DESCONHECIDO);
        setDescricao(ex.getMessage());
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Object getRetorno() {
        return retorno;
    }

    public void setRetorno(Object retorno) {
        this.retorno = retorno;
    }
    
    public enum Status{
        OK(0), 
        ERRO_AUTH(1),
        ERRO_BANCO(2),
        ERRO_DUPLICADO(3),
        ERRO_PARAMETROS_REQUERIDOS(4),
        ERRO_REGISTRO_NAO_EXISTE(5),
        ERRO_ESPACO_EXCEDIDO(6),
        ERRO_PARAMETRO_INVALIDO(7),
        ERRO_PERMISSAO_NEGADA(8),
        DESCONHECIDO(999);
        
        private Integer codigo=-1;

        private Status(Integer i) {
            this.codigo = i;
        }
        
        public int getCodigo(){
            return codigo;
        }
    }
}
