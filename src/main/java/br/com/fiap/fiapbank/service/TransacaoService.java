package br.com.fiap.fiapbank.service;

import br.com.fiap.fiapbank.dto.TransacaoDTO;
import br.com.fiap.fiapbank.entity.Transacao;

import java.util.List;

public interface TransacaoService {

    Transacao createTransacao(TransacaoDTO transacaoDTO);
    List<Transacao> findAll();
    String deleteTransacao(Long id);

}
