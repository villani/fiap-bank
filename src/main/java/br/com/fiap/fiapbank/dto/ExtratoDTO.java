package br.com.fiap.fiapbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ExtratoDTO {

    private String numeroCartao;
    private Date dataTransacao;
    private BigDecimal valor;

}
