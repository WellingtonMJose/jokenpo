package br.com.wmj.jokenpo.service.impl;

import br.com.wmj.jokenpo.dto.MoveRequest;
import br.com.wmj.jokenpo.dto.MoveResponse;
import br.com.wmj.jokenpo.entity.MoveEntity;
import br.com.wmj.jokenpo.entity.JogadorEntity;
import br.com.wmj.jokenpo.entity.mapper.MoveMapper;
import br.com.wmj.jokenpo.enumeration.EnumException;
import br.com.wmj.jokenpo.enumeration.EnumMovement;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.repository.MoveRepository;
import br.com.wmj.jokenpo.repository.JogadorRepository;
import br.com.wmj.jokenpo.service.MoveService;
import br.com.wmj.jokenpo.util.MoveSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MoveServiceImpl implements MoveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoveServiceImpl.class);

    private MoveRepository moveRepository;
    private JogadorRepository jogadorRepository;

    @Autowired
    public MoveServiceImpl(MoveRepository moveRepository, JogadorRepository jogadorRepository){
        this.moveRepository = moveRepository;
        this.jogadorRepository = jogadorRepository;
    }

    public MoveResponse insert(MoveRequest move) throws JokenpoException {
        if(Objects.isNull(move)
                || StringUtils.isEmpty(move.getNomeJogador())
                || StringUtils.isEmpty(move.getJogada())){
            LOGGER.error("Invalid movement");
            throw new JokenpoException(EnumException.MOVEMENT_INVALID);
        }
        LOGGER.debug("Move : {}", move.toString());

        // identify the jogador
        JogadorEntity jogadorEntity = this.jogadorRepository.findByName(move.getNomeJogador());

        // check if exists just one movement for these jogador
        this.verifyIfAlreadyMoved(jogadorEntity);

        // identify the movement
        EnumMovement movement = EnumMovement.getEnumMovementByName(move.getJogada());
        if(Objects.isNull(movement)){
            LOGGER.error("Movement not found");
            throw new JokenpoException(EnumException.MOVEMENT_NOT_FOUND);
        }

        // save entity object
        MoveEntity moveEntity = this.moveRepository.save(new MoveEntity(jogadorEntity, movement));

        // convert entity to response
        return MoveMapper.entityToResponse(moveEntity);
    }

    public List<MoveResponse> getAll() throws JokenpoException {
        LOGGER.debug("Finding all movements");
        List<MoveEntity> entityList = this.moveRepository.findAll();
        List<MoveResponse> response = new ArrayList<>();
        entityList
                .forEach(elem -> {
                    response.add(MoveMapper.entityToResponse(elem));
                });
        LOGGER.debug("Movements searched");
        return response;
    }

    public List<MoveResponse> deleteByPlayerName(String nomeJogador) throws JokenpoException {
        if(StringUtils.isEmpty(nomeJogador)){
            LOGGER.error("Player name invalid");
            throw new JokenpoException(EnumException.INVALID_PARAM);
        }
        LOGGER.debug("Finding movement by jogador name : {}", nomeJogador);
        MoveEntity entity = this.moveRepository.findByPlayerName(nomeJogador);
        LOGGER.debug("Deleting movement");
        if(this.moveRepository.delete(entity)){
            return this.getAll();
        };
        LOGGER.error("Error deleting movement");
        throw new JokenpoException(EnumException.MOVEMENT_DELETE_ERROR);
    }

    public void clearAll(){
        MoveSingleton.clear();
    }

    private void verifyIfAlreadyMoved(JogadorEntity jogador) throws JokenpoException {
        long count = this.moveRepository.findAll()
                .stream()
                .filter(elem ->
                        (elem.getJogador().getNome().compareToIgnoreCase(jogador.getNome()) == 0))
                .count();
        if(count > 0){
            LOGGER.error("Movement already exists for these jogador");
            throw new JokenpoException(EnumException.MOVEMENT_ALREADY_EXISTS);
        }
    }

}
