package br.com.wmj.jokenpo.service;

import br.com.wmj.jokenpo.dto.JokenpoResponse;
import br.com.wmj.jokenpo.dto.JogadorResponse;
import br.com.wmj.jokenpo.exception.JokenpoException;

import java.util.List;

public interface JokenpoService {

    List<JogadorResponse> clear() throws JokenpoException;

    JokenpoResponse play() throws JokenpoException;

}
