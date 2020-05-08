package br.com.wmj.jokenpo;


import br.com.wmj.jokenpo.structs.Retorno;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class DefaultWS {
     
    protected HttpServletRequest req;
    
    protected HttpServletResponse resp;
    
    protected Gson gson = getGson();
    
    public void addCoors(){
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        resp.addHeader("Access-Control-Max-Age", "1209600");
    }

    public void addCoors(HttpServletResponse resp){
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        resp.addHeader("Access-Control-Max-Age", "1209600");
    }

    public Gson getGson(){
        return new GsonBuilder().setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    }
    
    
    public String sendResponse(Retorno retorno){
        addCoors();
        return gson.toJson(retorno);
    }

    public String sendSucess(Object message){
        addCoors();
        Retorno retorno = new Retorno();
        retorno.setStatus(Retorno.Status.OK);
        retorno.setRetorno(message);
        return gson.toJson(retorno);
    }
    
    public String sendError(Retorno retorno){
        addCoors();
        return gson.toJson(retorno);
    }
    
    public String sendDataBaseError(String message){
        addCoors();
        Retorno retorno = new Retorno();
        retorno.setStatus(Retorno.Status.ERRO_BANCO);
        retorno.setRetorno(message);
        return gson.toJson(retorno);
    }
    
    public String sendParameterError(String message){
        addCoors();
        Retorno retorno = new Retorno();
        retorno.setStatus(Retorno.Status.ERRO_PARAMETRO_INVALIDO);
        retorno.setRetorno(message);
        return gson.toJson(retorno);
    }
    
    public String sendPermissionError(String message){
        addCoors();
        Retorno retorno = new Retorno();
        retorno.setStatus(Retorno.Status.ERRO_PERMISSAO_NEGADA);
        retorno.setRetorno(message);
        return gson.toJson(retorno);
    }
    
    public String sendUnknowDataError(String message){
        addCoors();
        Retorno retorno = new Retorno();
        retorno.setStatus(Retorno.Status.ERRO_REGISTRO_NAO_EXISTE);
        retorno.setRetorno(message);
        return gson.toJson(retorno);
    }
}