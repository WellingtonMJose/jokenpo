package br.com.wmj.jokenpo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServlet;
import br.com.wmj.jokenpo.structs.Retorno;

public abstract class DefaultServlet extends HttpServlet{
    protected Gson gson = getGson();
    
    public Gson getGson(){
        return gson = new GsonBuilder().setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    }
    
    public String sendSucess(Object object){
        Retorno retorno = new Retorno();
        retorno.setStatus(Retorno.Status.OK);
        retorno.setRetorno(object);
        return gson.toJson(retorno);
    }
    
    public String sendError(Retorno retorno){
        return gson.toJson(retorno);
    }
}

