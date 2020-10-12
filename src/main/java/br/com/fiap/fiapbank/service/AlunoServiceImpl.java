package br.com.fiap.fiapbank.service;

import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fiap.fiapbank.dto.AlunoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.fiapbank.entity.Aluno;
import br.com.fiap.fiapbank.repository.AlunoRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    @Override
    public AlunoDTO saveAluno(AlunoDTO alunoDTO) {

        Aluno busca = alunoRepository.findByMatricula(alunoDTO.getMatricula());

        if(busca != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Aluno aluno = new Aluno(alunoDTO);
        Aluno alunoBanco = alunoRepository.save(aluno);
        return new AlunoDTO(alunoBanco);
    }

    @Override
    public Iterable<AlunoDTO> findAll() {

        return alunoRepository.findAll().stream().map(registro -> new AlunoDTO(registro)).collect(Collectors.toList());
    }

    @Override
    public AlunoDTO findByMatricula(String matricula) {
        Aluno aluno = (alunoRepository.findByMatricula(matricula));
        if(aluno != null){
            AlunoDTO alunoDTO = new AlunoDTO(aluno);
            return alunoDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(String matricula) {
        Aluno aluno = alunoRepository.findByMatricula(matricula);
        if(aluno != null){
            alunoRepository.delete(aluno);
            return "Aluno excluído";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public AlunoDTO updateAluno(String matriculaAtual,AlunoDTO alunoDTO) {

        Aluno aluno = alunoRepository.findByMatricula(matriculaAtual);
        if(aluno != null){
            aluno.setEmail(alunoDTO.getEmail());
            aluno.setNome(alunoDTO.getNome());
            aluno.setMatricula(alunoDTO.getMatricula());
            alunoRepository.save(aluno);
            return new AlunoDTO(aluno);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


}
