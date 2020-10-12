package br.com.fiap.fiapbank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "tbl_cartao" ,
        uniqueConstraints = {@UniqueConstraint(columnNames = {"numero_cartao"}) })
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_cartao")
    private String numeroCartao;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
