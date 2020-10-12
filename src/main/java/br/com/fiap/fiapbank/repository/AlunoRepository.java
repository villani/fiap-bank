package br.com.fiap.fiapbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.fiapbank.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

//    Optional<Aluno> findByIdMatricula(@Param("idMatricula") Long idMatricula);
    Aluno findByMatricula(@Param("matricula") String matricula);
}
