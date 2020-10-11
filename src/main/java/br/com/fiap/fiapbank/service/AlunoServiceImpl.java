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
        Aluno aluno = new Aluno(alunoDTO);
        Aluno alunoBanco = alunoRepository.save(aluno);
        return new AlunoDTO(alunoBanco);
    }

    @Override
    public Iterable<AlunoDTO> findAll() {

        return alunoRepository.findAll().stream().map(registro -> new AlunoDTO(registro)).collect(Collectors.toList());
    }

    @Override
    public AlunoDTO findById(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            AlunoDTO alunoDTO = new AlunoDTO(aluno.get());
            return alunoDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            alunoRepository.delete(aluno.get());
            return "Aluno excluído";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public AlunoDTO updateAluno(Long id,AlunoDTO alunoDTO) {

        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            aluno.get().setEmail(alunoDTO.getEmail());
            aluno.get().setNome(alunoDTO.getNome());
            aluno.get().setMatricula(alunoDTO.getMatricula());
            alunoRepository.save(aluno.get());
            return new AlunoDTO(aluno.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


}
