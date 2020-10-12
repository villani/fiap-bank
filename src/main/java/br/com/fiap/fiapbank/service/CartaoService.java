package br.com.fiap.fiapbank.service;

import br.com.fiap.fiapbank.dto.CreateCartaoDTO;
import br.com.fiap.fiapbank.entity.Cartao;

import java.util.List;


public interface CartaoService {

    Cartao saveCartao(CreateCartaoDTO createCartaoDTO);
    List<CreateCartaoDTO> findAll();
    String excluircartao(String numeroCartao);
    Cartao findByNumeroCartao(String numeroCartao);
    CreateCartaoDTO updateCartao(String numeroCartaoAtual, CreateCartaoDTO createCartaoDTO);
    List<CreateCartaoDTO> findByAluno(String matricula);

}
