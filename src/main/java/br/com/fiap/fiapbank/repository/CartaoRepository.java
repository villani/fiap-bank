package br.com.fiap.fiapbank.repository;

import br.com.fiap.fiapbank.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Cartao findByNumeroCartao(@Param("numeroCartao") String numeroCartao);

    List<Cartao> findByAlunoMatricula(@Param("matricula") String matricula);

}
