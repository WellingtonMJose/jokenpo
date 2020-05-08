package br.com.wmj.jokenpo.entity.mapper;

import br.com.wmj.jokenpo.dto.JogadorRequest;
import br.com.wmj.jokenpo.dto.JogadorResponse;
import br.com.wmj.jokenpo.entity.JogadorEntity;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JogadorMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogadorMapper.class);

    private static ModelMapper MAPPER = new ModelMapper();

    public static JogadorEntity requestToEntity(JogadorRequest jogadorRequest){
        LOGGER.debug("Converting: request object to entity object");
        return MAPPER.map(jogadorRequest, JogadorEntity.class);
    }

    public static JogadorResponse entityToResponse(JogadorEntity entity) {
        LOGGER.debug("Converting: entity object to response object");
        return MAPPER.map(entity, JogadorResponse.class);
    }

}
