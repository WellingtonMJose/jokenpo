package br.com.wmj.jokenpo.controller;

import br.com.wmj.jokenpo.dto.JogadorRequest;
import br.com.wmj.jokenpo.dto.api.ApiResponse;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.service.impl.JogadorServiceImpl;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogador")
@CrossOrigin(origins = "*")
public class JogadorController {
    
    private JogadorServiceImpl jogadorService;

    @Autowired
    public JogadorController(JogadorServiceImpl jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> insert(@Valid @RequestBody JogadorRequest jogadorRequest)
            throws JokenpoException {
        return ResponseEntity.ok(
                new ApiResponse<>(this.jogadorService.insert(jogadorRequest)));
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> delete(@PathParam("nomeJogador") String nomeJogador) throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jogadorService.deleteByName(nomeJogador)));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jogadorService.getAll()));
    }

    
}
