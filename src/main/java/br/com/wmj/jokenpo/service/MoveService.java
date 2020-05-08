package br.com.wmj.jokenpo.service;

import br.com.wmj.jokenpo.dto.MoveRequest;
import br.com.wmj.jokenpo.dto.MoveResponse;
import br.com.wmj.jokenpo.exception.JokenpoException;

import java.util.List;

public interface MoveService {

    MoveResponse insert(MoveRequest move) throws JokenpoException;

    List<MoveResponse> getAll() throws JokenpoException;

    List<MoveResponse> deleteByPlayerName(String nomeJogador) throws JokenpoException;

    void clearAll();

}
