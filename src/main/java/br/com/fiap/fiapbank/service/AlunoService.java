package br.com.fiap.fiapbank.service;

import br.com.fiap.fiapbank.dto.AlunoDTO;
import br.com.fiap.fiapbank.entity.Aluno;

import java.util.List;

public interface AlunoService {

    AlunoDTO saveAluno(AlunoDTO alunoDTO);
    List<AlunoDTO> findAll();
    AlunoDTO findByMatricula(String matricula);
    String delete(String matricula);
    AlunoDTO updateAluno(String matriculaAtual, AlunoDTO alunoDTO);
    
}
