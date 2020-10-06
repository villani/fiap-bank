package br.com.fiap.fiapbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tblAluno") @Entity
public class Aluno {

    @Id
    private Long idMatricula;

    @Column
    private String nome;

    @Column
    private String email;

}