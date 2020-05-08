package br.com.wmj.jokenpo.controller;

import br.com.wmj.jokenpo.dto.api.ApiResponse;
import br.com.wmj.jokenpo.exception.JokenpoException;
import br.com.wmj.jokenpo.service.impl.JokenpoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogar")
@CrossOrigin(origins = "*")
public class JokenpoController {

    private JokenpoServiceImpl jokenpoService;

    @Autowired
    public JokenpoController(JokenpoServiceImpl jokenpoService) {
        this.jokenpoService = jokenpoService;
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Object> reset() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jokenpoService.clear()));
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> play() throws JokenpoException {
        return ResponseEntity.ok(new ApiResponse<>(this.jokenpoService.play()));
    }

}
