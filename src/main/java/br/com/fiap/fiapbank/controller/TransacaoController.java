package br.com.fiap.fiapbank.controller;

import br.com.fiap.fiapbank.dto.TransacaoDTO;
import br.com.fiap.fiapbank.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity<?> createTransacao(@RequestBody TransacaoDTO transacaoDTO){
        return ResponseEntity.ok().body(transacaoService.createTransacao(transacaoDTO));
    }

    @GetMapping()
    public @ResponseBody ResponseEntity<?> listarTransacoes(){
        return ResponseEntity.ok().body(transacaoService.findAll());
    }

    @DeleteMapping("{id}")
    public @ResponseBody ResponseEntity<?> deletarTransacao (@PathVariable Long id ){
        return ResponseEntity.ok().body(transacaoService.deleteTransacao(id));
    }

}
