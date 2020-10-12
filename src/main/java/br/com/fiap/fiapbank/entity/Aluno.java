package br.com.fiap.fiapbank.entity;

import javax.persistence.*;

import br.com.fiap.fiapbank.dto.AlunoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Table(name = "tbl_aluno") @Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    @CreatedDate
    private Date dataCriacao;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private Date dataAtualizacao;

    public Aluno(AlunoDTO alunoDTO){
        this.nome = alunoDTO.getNome();
        this.email = alunoDTO.getEmail();
        this.matricula = alunoDTO.getMatricula();
    }

}