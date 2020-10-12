package br.com.fiap.fiapbank.controller;

import br.com.fiap.fiapbank.dto.AlunoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.fiapbank.service.AlunoService;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @GetMapping()
    public @ResponseBody ResponseEntity<?> listar() {
        return ResponseEntity
                .ok()
                .body(alunoService.findAll());
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<?> gravar(@RequestBody AlunoDTO alunoDTO){
       return ResponseEntity.ok().body(alunoService.saveAluno(alunoDTO));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> buscarAluno(@PathVariable Long id){
        return ResponseEntity.ok().body(alunoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> excluir(@PathVariable Long id){
        return ResponseEntity.ok().body(alunoService.delete(id));
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<?> atualizarAluno(@PathVariable Long id,
                                                          @RequestBody AlunoDTO alunoDTO){
        return ResponseEntity.ok().body(alunoService.updateAluno(id,alunoDTO));
    }
    
}
