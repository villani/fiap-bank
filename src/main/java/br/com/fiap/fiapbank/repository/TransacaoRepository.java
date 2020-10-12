package br.com.fiap.fiapbank.repository;

import br.com.fiap.fiapbank.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

    List<Transacao> findByCartaoNumeroCartao(@Param("numeroCartao") String numeroCartao);

}
