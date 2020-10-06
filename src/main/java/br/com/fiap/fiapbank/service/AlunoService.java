package br.com.fiap.fiapbank.service;

import br.com.fiap.fiapbank.entity.Aluno;

public interface AlunoService {

    Aluno saveAluno(Aluno aluno);
    Iterable<Aluno> findAll();
    Aluno findByIdMatricula(Long idMatricula);
    String deleteAluno(Long idMatricula);
    
    
}
