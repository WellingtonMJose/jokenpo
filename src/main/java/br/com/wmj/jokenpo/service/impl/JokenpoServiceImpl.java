package br.com.wmj.jokenpo.service.impl;

import br.com.wmj.jokenpo.dto.JokenpoResponse;
import br.com.wmj.jokenpo.dto.MoveResponse;
import br.com.wmj.jokenpo.dto.JogadorResponse;
import br.com.wmj.jokenpo.enumeration.EnumException;
import br.com.wmj.jokenpo.enumeration.EnumMovement;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.service.JokenpoService;
import br.com.wmj.jokenpo.util.MoveSingleton;
import br.com.wmj.jokenpo.util.JogadorSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JokenpoServiceImpl implements JokenpoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JokenpoServiceImpl.class);

    private static final String ZERO_WINS = "NINGUÉM GANHOU!";
    private static final String ONE_WINS = " É O VENCEDOR";
    private static final String MANY_WINS = "OS VENCEDORES: ";
    private static final String MANY_WINS_SEPARATOR = " / ";

    private JogadorServiceImpl jogadorService;
    private MoveServiceImpl moveService;

    @Autowired
    public JokenpoServiceImpl(JogadorServiceImpl jogadorService,
                              MoveServiceImpl moveService){
        this.jogadorService = jogadorService;
        this.moveService = moveService;
    }

    public List<JogadorResponse> clear() throws JokenpoException {
        LOGGER.debug("Erasing all data");
        MoveSingleton.clear();
        JogadorSingleton.clear();
        LOGGER.debug("Data erased");
        return this.jogadorService.getAll();
    }

    public JokenpoResponse play() throws JokenpoException {
        this.checkRequirements();
        List<String> winners = new ArrayList<>();
        LOGGER.debug("Generating result");
        this.moveService.getAll()
                .forEach(obj -> {
                    try {
                        if(checkIfIsTheWinner(obj.getJogada().getFraqueza())){
                            winners.add(obj.getJogador().getNome());
                        }
                    } catch (JokenpoException e) {
                        LOGGER.error("Error detecting winners - Player Name : {} - Error Message : {}",
                                obj.getJogador().getNome(), e.getMessage());
                    }
                });
        LOGGER.debug("Result generated");

        JokenpoResponse gameResult = new JokenpoResponse(this.getWinnersMessage(winners),
                this.getHistoryFromMovements(this.moveService.getAll()));
        LOGGER.debug("Mensagem dos vencedores formatada");

        LOGGER.debug("Apagando dados de movimentos");
        MoveSingleton.clear();

        LOGGER.debug("Rodada terminada");
        return gameResult;
    }

    private void checkRequirements() throws JokenpoException {
        if(this.jogadorService.getAll().size() == 0){
            throw new JokenpoException(EnumException.NOBODY_JOGAR);
        } else if (this.jogadorService.getAll().size() <= 1){
            throw new JokenpoException(EnumException.INSUFFICIENT_JOGADOR);
        } else if (this.moveService.getAll().size() <= 1){
            throw new JokenpoException(EnumException.INSUFFICIENT_MOVEMENTS);
        } else if (this.moveService.getAll().size() != this.jogadorService.getAll().size()){
            throw new JokenpoException(EnumException.JOGADORS_PENDING);
        }
    }

    private Boolean checkIfIsTheWinner(List<EnumMovement> weakness) throws JokenpoException {
        for (EnumMovement enumMovement : weakness) {
            LOGGER.debug("Checando fraqueza : {}", enumMovement.getNome());
            for(MoveResponse resp : this.moveService.getAll()){
                if(resp.getJogada().getNome().compareTo(enumMovement.getNome()) == 0){
                    LOGGER.debug("Perdedor - Perdido para {} - {}", resp.getJogador().getNome(), enumMovement.getNome());
                    return false;
                }
            }
        }
        LOGGER.debug("Ganhador detectado");
        return true;
    }

    private String getWinnersMessage(List<String> winners){
        String message = "";
        if(winners.size() == 0){
            message = ZERO_WINS;
        } else if(winners.size() == 1) {
            message = winners.get(0).toUpperCase().trim() + ONE_WINS;
        } else {
            message = MANY_WINS;
            int counter = 0;
            for(String name : winners){
                counter++;
                if(counter == winners.size()){
                    message = message + name;
                } else {
                    message = message + name + MANY_WINS_SEPARATOR;
                }
            }
        }
        return message;
    }

    private List<String> getHistoryFromMovements(List<MoveResponse> list) {
        List<String> result = new ArrayList<>();
        for(MoveResponse resp : list){
            String message = resp.getJogador().getNome() + " (" + resp.getJogada().getNome() + ")";
            result.add(message);
        }
        return result;
    }

}
