package br.com.wmj.jokenpo.repository;

import br.com.wmj.jokenpo.entity.MoveEntity;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.util.MoveSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import br.com.wmj.jokenpo.enumeration.EnumException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@NoRepositoryBean
public class MoveRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoveRepository.class);

    public MoveEntity save(MoveEntity entity) throws JokenpoException {
        if(MoveSingleton.getInstance() != null
                && MoveSingleton.getInstance().add(entity))
            return entity;
        LOGGER.error("Error saving");
        throw new JokenpoException(EnumException.MOVEMENT_SAVE_ERROR);
    }

    public boolean delete(MoveEntity entity) throws JokenpoException {
        if(MoveSingleton.getInstance() == null) {
            LOGGER.error("Error deleting");
            throw new JokenpoException(EnumException.MOVEMENT_DELETE_ERROR);
        }
        return MoveSingleton.getInstance().remove(entity);
    }

    public List<MoveEntity> findAll() throws JokenpoException {
        if(MoveSingleton.getInstance() == null) {
            LOGGER.error("Error finding all movements");
            throw new JokenpoException(EnumException.MOVEMENT_FIND_ALL_ERROR);
        }
        return MoveSingleton.getInstance();
    }

    public MoveEntity findByPlayerName(String nomeJogador) throws JokenpoException {
        List<MoveEntity> list = findAll().stream()
                .filter(elem -> (elem.getJogador().getNome().compareToIgnoreCase(nomeJogador) == 0))
                .collect(Collectors.toList());
        Optional<MoveEntity> opt = list.stream().findFirst();
        if(opt.isPresent()){
            return opt.get();
        }
        LOGGER.error("Player movement not found : {}", nomeJogador);
        throw new JokenpoException(EnumException.MOVEMENT_NOT_FOUND);
    }

}
