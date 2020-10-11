package br.com.fiap.fiapbank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tblAluno") 
@Getter @Setter @NoArgsConstructor
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

    public Aluno(Long idMatricula, String nome, String email) {
        this.idMatricula = idMatricula;
        this.nome = nome;
        this.email = email;
    }

}