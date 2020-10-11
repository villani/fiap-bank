package br.com.fiap.fiapbank.dto;

import br.com.fiap.fiapbank.entity.Aluno;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AlunoDTO {

        private String nome;
        private String email;
        private String matricula;

        public AlunoDTO(Aluno aluno){
                this.nome = aluno.getNome();
                this.email = aluno.getEmail();
                this.matricula = aluno.getMatricula();
        }

}
