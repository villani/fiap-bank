package br.com.fiap.fiapbank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TransacaoDTO {

    private BigDecimal valor;
    private String numerocartao;

}
