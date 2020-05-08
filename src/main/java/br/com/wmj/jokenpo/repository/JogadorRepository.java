package br.com.wmj.jokenpo.repository;

import br.com.wmj.jokenpo.entity.JogadorEntity;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.enumeration.EnumException;
import br.com.wmj.jokenpo.util.JogadorSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@NoRepositoryBean
public class JogadorRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorRepository.class);

    public JogadorEntity save(JogadorEntity entity) throws JokenpoException {
        if (JogadorSingleton.getInstance() != null
                && JogadorSingleton.getInstance().add(entity)) {
            return entity;
        }
        LOGGER.error("Error saving");
        throw new JokenpoException(EnumException.JOGADOR_SAVE_ERROR);
    }

    public boolean delete(JogadorEntity entity) throws JokenpoException {
        if (JogadorSingleton.getInstance() == null) {
            LOGGER.error("Error deleting");
            throw new JokenpoException(EnumException.JOGADOR_DELETE_ERROR);
        }
        return JogadorSingleton.getInstance().remove(entity);
    }

    public List<JogadorEntity> findAll() throws JokenpoException {
        if (JogadorSingleton.getInstance() == null) {
            LOGGER.error("Error finding all players");
            throw new JokenpoException(EnumException.JOGADOR_FIND_ALL_ERROR);
        }
        return JogadorSingleton.getInstance();
    }

    public JogadorEntity findByName(String nome) throws JokenpoException {
        List<JogadorEntity> list = findAll().stream()
                .filter(elem -> (elem.getNome().compareToIgnoreCase(nome) == 0))
                .collect(Collectors.toList());
        Optional<JogadorEntity> opt = list.stream().findFirst();
        if (opt.isPresent()) {
            return opt.get();
        }
        LOGGER.info("Jogador nao encontrado : {}", nome);
        throw new JokenpoException(EnumException.JOGADOR_NOT_FOUND);
    }

}
