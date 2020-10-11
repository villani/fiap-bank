package br.com.fiap.fiapbank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.fiapbank.dto.AlunoDTO;
import br.com.fiap.fiapbank.entity.Aluno;
import br.com.fiap.fiapbank.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public Iterable<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno findByIdMatricula(Long idMatricula) {
        Optional<Aluno> aluno = alunoRepository.findByIdMatricula(idMatricula);
        if (aluno.isPresent()) {
            return aluno.get();
        } else {
            return null;
        }
    }

    @Override
    public Aluno save(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno(
            alunoDTO.getIdMatricula(),
            alunoDTO.getNome(),
            alunoDTO.getEmail());
        return alunoRepository.save(aluno);
    }

    @Override
    public String delete(Long idMatricula) {
        Optional<Aluno> aluno = alunoRepository.findByIdMatricula(idMatricula);
        if (aluno.isPresent()) {
            alunoRepository.delete(aluno.get());
            return "Aluno removido";
        }
        return "Aluno não encontrado";
    }
    
}
