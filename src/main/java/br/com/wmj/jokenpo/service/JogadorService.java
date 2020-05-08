package br.com.wmj.jokenpo.service;

import br.com.wmj.jokenpo.dto.JogadorRequest;
import br.com.wmj.jokenpo.dto.JogadorResponse;
import br.com.wmj.jokenpo.entity.JogadorEntity;
import br.com.wmj.jokenpo.exception.JokenpoException;

import java.util.List;

public interface JogadorService {

    JogadorResponse insert(JogadorRequest player) throws JokenpoException;

    List<JogadorResponse> getAll() throws JokenpoException;

    JogadorEntity getEntityByName(String nome) throws JokenpoException;

    List<JogadorResponse> deleteByName(String nome) throws JokenpoException;

    void clearAll();

}
