package br.com.fiap.fiapbank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tbl_transacao")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_transacao", nullable = false, updatable = false)
    @CreatedDate
    private Date dataTransacao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;

}
