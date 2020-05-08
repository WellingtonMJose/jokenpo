package br.com.wmj.jokenpo.controller;

import br.com.wmj.jokenpo.dto.MoveRequest;
import br.com.wmj.jokenpo.dto.api.ApiResponse;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.service.impl.MoveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/mover")
@CrossOrigin(origins = "*")
public class MoveController {

    private MoveServiceImpl moveService;

    @Autowired
    public MoveController(MoveServiceImpl moveService) {
        this.moveService = moveService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> insert(@Valid @RequestBody MoveRequest moveRequest)
            throws JokenpoException {
        return ResponseEntity.ok(
                new ApiResponse<>(this.moveService.insert(moveRequest)));
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> delete(@PathParam("nomeJogador") String nomeJogador) throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.moveService.deleteByPlayerName(nomeJogador)));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.moveService.getAll()));
    }

}
