package br.com.fiap.fiapbank;

import br.com.fiap.fiapbank.controller.AlunoController;
import br.com.fiap.fiapbank.controller.CartaoController;
import br.com.fiap.fiapbank.controller.TransacaoController;
import br.com.fiap.fiapbank.dto.AlunoDTO;
import br.com.fiap.fiapbank.dto.CreateCartaoDTO;
import br.com.fiap.fiapbank.dto.TransacaoDTO;
import br.com.fiap.fiapbank.entity.Aluno;
import br.com.fiap.fiapbank.repository.AlunoRepository;
import br.com.fiap.fiapbank.service.AlunoService;
import br.com.fiap.fiapbank.service.CartaoService;
import br.com.fiap.fiapbank.service.TransacaoService;
import br.com.fiap.fiapbank.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@SpringBootApplication
@EnableBatchProcessing
public class FiapBankApplication {

    Logger logger = LoggerFactory.getLogger(FiapBankApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FiapBankApplication.class, args);
    }

    @Autowired
    AlunoService alunoService;

    @Autowired
	CartaoService cartaoService;

    @Autowired
    TransacaoService transacaoService;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("lista_alunos.txt").getFile());
            AlunoController alunoController = new AlunoController(alunoService);
			CartaoController cartaoController = new CartaoController(cartaoService);
            TransacaoController transacaoController = new TransacaoController(transacaoService);
            try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {
                String line;
                String nome = "";
                String email = "";
                String matricula = "";
                String numeroCartao = "";
                while ((line = br.readLine()) != null) {
                    nome = Util.getNameFromLine(line).replace("-", "");
                    if(!nome.isEmpty()) {
                        System.out.println(nome);
						email = nome.trim().toLowerCase() + "@fiap.com.br";
						matricula = Util.getRMFromLine(line);
						numeroCartao = matricula + "-00";
						//criando aluno
						AlunoDTO alunoDTO = new AlunoDTO(nome, email, matricula);
						alunoController.gravar(alunoDTO);
						CreateCartaoDTO createCartaoDTO = new CreateCartaoDTO(numeroCartao,matricula);
						cartaoController.saveCartao(createCartaoDTO);
                        TransacaoDTO transacaoDTO = new TransacaoDTO(Util.generateRandomValue(),numeroCartao);
                        transacaoController.createTransacao(transacaoDTO);
					}
                }
            }
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     Tasklet tasklet) {
        return stepBuilderFactory.get("t24")
                .tasklet(tasklet)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   Step step) {
        return jobBuilderFactory.get("t24")
                .start(step)
                .build();
    }

}
