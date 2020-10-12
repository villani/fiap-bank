package br.com.fiap.fiapbank.service;

import br.com.fiap.fiapbank.dto.TransacaoDTO;
import br.com.fiap.fiapbank.entity.Cartao;
import br.com.fiap.fiapbank.entity.Transacao;
import br.com.fiap.fiapbank.repository.CartaoRepository;
import br.com.fiap.fiapbank.repository.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository,
                                CartaoRepository cartaoRepository){
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @Override
    public Transacao createTransacao(TransacaoDTO transacaoDTO) {

        Cartao cartao = cartaoRepository.findByNumeroCartao(transacaoDTO.getNumerocartao());

        if(cartao == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Transacao transacao = new Transacao();
        transacao.setCartao(cartao);
        transacao.setValor(transacaoDTO.getValor());

        return transacaoRepository.save(transacao);
    }

    @Override
    public List<Transacao> findAll() {
        return transacaoRepository.findAll();
    }

    @Override
    public String deleteTransacao(Long id) {

        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        transacaoRepository.delete(transacao);
        return "Transação excluída com sucesso";
    }
}
