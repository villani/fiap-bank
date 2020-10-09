package br.com.fiap.fiapbank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tblAluno") @Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Aluno {

    @Id
    private Long idMatricula;

    @Column
    private String nome;

    @Column
    private String email;

    @Column(name = "data_criacao", updatable = false, nullable = false)
    @CreatedDate
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private Date dataAtualizacao;

}