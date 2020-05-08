package br.com.wmj.jokenpo.service.impl;

import br.com.wmj.jokenpo.dto.JogadorRequest;
import br.com.wmj.jokenpo.dto.JogadorResponse;
import br.com.wmj.jokenpo.entity.JogadorEntity;
import br.com.wmj.jokenpo.entity.mapper.JogadorMapper;
import br.com.wmj.jokenpo.enumeration.EnumException;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.repository.JogadorRepository;
import br.com.wmj.jokenpo.util.JogadorSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import br.com.wmj.jokenpo.service.JogadorService;

@Service
public class JogadorServiceImpl implements JogadorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorServiceImpl.class);

    private JogadorRepository jogadorRepository;
    private MoveServiceImpl moveService;

    @Autowired
    public JogadorServiceImpl(JogadorRepository jogadorRepository, MoveServiceImpl moveService){
        this.jogadorRepository = jogadorRepository;
        this.moveService = moveService;
    }

    @Override
    public JogadorResponse insert(JogadorRequest jogador) throws JokenpoException {
        if(this.verifyIfAlreadyExistsByName(jogador.getNome())){
            LOGGER.error("Player already exists");
            throw new JokenpoException(EnumException.JOGADOR_ALREADY_EXISTS);
        }
        LOGGER.debug("Insert new jogador - Request: " + jogador.toString());
        JogadorEntity entity = JogadorMapper.requestToEntity(jogador);
        LOGGER.debug("Inserting jogador");
        entity = this.jogadorRepository.save(entity);
        LOGGER.debug("Creating response object");
        return JogadorMapper.entityToResponse(entity);
    }

    @Override
    public List<JogadorResponse> getAll() throws JokenpoException {
        LOGGER.debug("Finding all jogadors");
        List<JogadorEntity> entityList = this.jogadorRepository.findAll();
        List<JogadorResponse> response = new ArrayList<>();
        entityList
                .forEach(elem -> {
                    response.add(JogadorMapper.entityToResponse(elem));
                });
        LOGGER.debug("Players filtered");
        return response;
    }

    @Override
    public JogadorEntity getEntityByName(String name) throws JokenpoException {
        LOGGER.debug("Finding jogador by name : {}", name);
        return this.jogadorRepository.findByName(name);
    }

    @Override
    public List<JogadorResponse> deleteByName(String name) throws JokenpoException {
        if(StringUtils.isEmpty(name)){
            LOGGER.error("Param name invalid");
            throw new JokenpoException(EnumException.INVALID_PARAM);
        }
        try {
            this.moveService.deleteByPlayerName(name);
        } catch (JokenpoException ex){
            LOGGER.debug("Player without movement already");
        }
        LOGGER.debug("Finding jogador by name : {}", name);
        JogadorEntity entity = this.jogadorRepository.findByName(name);
        LOGGER.debug("Removing jogador");
        if(this.jogadorRepository.delete(entity)){
            return this.getAll();
        }
        LOGGER.error("Error deleting jogador");
        throw new JokenpoException(EnumException.JOGADOR_DELETE_ERROR);
    }

    @Override
    public void clearAll(){
        JogadorSingleton.clear();
    }

    private Boolean verifyIfAlreadyExistsByName(String name) {
        try {
            if (!Objects.isNull(this.jogadorRepository.findByName(name))) {
                return true;
            }
        } catch (JokenpoException e) {
            return false;
        }
        return false;
    }

}
