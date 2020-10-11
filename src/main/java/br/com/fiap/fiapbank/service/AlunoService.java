package br.com.fiap.fiapbank.service;

import br.com.fiap.fiapbank.dto.AlunoDTO;
import br.com.fiap.fiapbank.entity.Aluno;

public interface AlunoService {

    Aluno save(AlunoDTO aluno);
    Iterable<Aluno> findAll();
    Aluno findByIdMatricula(Long idMatricula);
    String delete(Long idMatricula);
    
    
}
