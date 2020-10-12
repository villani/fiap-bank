package br.com.fiap.fiapbank.dto;

import br.com.fiap.fiapbank.entity.Cartao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CreateCartaoDTO {

    private String numeroCartao;
    private String matriculaAluno;

    public CreateCartaoDTO(Cartao cartao){
        this.numeroCartao = cartao.getNumeroCartao();
        this.matriculaAluno = cartao.getAluno().getMatricula();
    }

}
