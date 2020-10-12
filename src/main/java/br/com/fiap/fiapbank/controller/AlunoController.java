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
        return ResponseEntity.ok().body(alunoService.findAll());
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<?> gravar(@RequestBody AlunoDTO alunoDTO){
       return ResponseEntity.ok().body(alunoService.saveAluno(alunoDTO));
    }

    @GetMapping("{matricula}")
    public @ResponseBody ResponseEntity<?> buscarAluno(@PathVariable String matricula){
        return ResponseEntity.ok().body(alunoService.findByMatricula(matricula));
    }

    @DeleteMapping("{matricula}")
    public @ResponseBody ResponseEntity<?> excluir(@PathVariable String matricula){
        return ResponseEntity.ok().body(alunoService.delete(matricula));
    }

    @PutMapping("{matriculaAtual}")
    public @ResponseBody ResponseEntity<?> atualizarAluno(@PathVariable String matriculaAtual,
            @RequestBody AlunoDTO alunoDTO){
        return ResponseEntity.ok().body(alunoService.updateAluno(matriculaAtual,alunoDTO));
    }

    
}
