package br.com.fiap.fiapbank.service;

import br.com.fiap.fiapbank.dto.CreateCartaoDTO;
import br.com.fiap.fiapbank.entity.Aluno;
import br.com.fiap.fiapbank.entity.Cartao;
import br.com.fiap.fiapbank.repository.AlunoRepository;
import br.com.fiap.fiapbank.repository.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoServiceImpl implements CartaoService {

    private final AlunoRepository alunoRepository;
    private final CartaoRepository cartaoRepository;

    public CartaoServiceImpl(AlunoRepository alunoRepository,
                             CartaoRepository cartaoRepository){
        this.alunoRepository = alunoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @Override
    public Cartao saveCartao(CreateCartaoDTO createCartaoDTO) {

        Aluno aluno = alunoRepository.findByMatricula(createCartaoDTO.getMatriculaAluno());
        Cartao busca = cartaoRepository.findByNumeroCartao(createCartaoDTO.getNumeroCartao());

        if(aluno == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(busca != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Cartao cartao = new Cartao();
        cartao.setAluno(aluno);
        cartao.setNumeroCartao(createCartaoDTO.getNumeroCartao());

        return cartaoRepository.save(cartao);
    }

    @Override
    public List<CreateCartaoDTO> findAll() {

        return cartaoRepository
                .findAll()
                .stream()
                .map(registro -> new CreateCartaoDTO(registro)).collect(Collectors.toList());
    }

    @Override
    public String excluircartao(String numeroCartao) {

        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if(cartao == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cartaoRepository.delete(cartao);
        return "cartão excluído";
    }

    @Override
    public Cartao findByNumeroCartao(String numeroCartao) {

        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if(cartao == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return cartao;
    }

    @Override
    public CreateCartaoDTO updateCartao(String numeroCartaoAtual, CreateCartaoDTO createCartaoDTO) {

        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartaoAtual);

        if(cartao == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Aluno aluno = alunoRepository.findByMatricula(createCartaoDTO.getMatriculaAluno());

        if(aluno == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        cartao.setNumeroCartao(createCartaoDTO.getNumeroCartao());
        cartao.setAluno(aluno);
        cartaoRepository.save(cartao);

        return new CreateCartaoDTO(cartao);
    }

    @Override
    public List<CreateCartaoDTO> findByAluno(String matricula) {

        return cartaoRepository.findByAlunoMatricula(matricula)
                .stream()
                .map(registro-> new CreateCartaoDTO(registro)).collect(Collectors.toList());
    }
}
