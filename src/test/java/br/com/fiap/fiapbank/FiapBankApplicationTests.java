package br.com.fiap.fiapbank;

import br.com.fiap.fiapbank.dto.AlunoDTO;
import br.com.fiap.fiapbank.entity.Aluno;
import br.com.fiap.fiapbank.repository.AlunoRepository;
import br.com.fiap.fiapbank.service.AlunoService;
import br.com.fiap.fiapbank.service.AlunoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class FiapBankApplicationTests {

	 @Test
	 public void findAllAlunos() {
         AlunoRepository alunoRepository = Mockito.mock(AlunoRepository.class);
         List<Aluno> alunoList = new ArrayList<>();
         AlunoDTO alunoDTO1 = new AlunoDTO("Guilherme", "guilherme@fiap.com.br","336810");
         AlunoDTO alunoDTO2 = new AlunoDTO("Fulano", "fulano@fiap.com.br","336811");
         Aluno aluno1 = new Aluno(alunoDTO1);
         Aluno aluno2 = new Aluno(alunoDTO2);
         alunoList.add(aluno1);
         alunoList.add(aluno2);

        Mockito.when(alunoRepository.findAll()).thenReturn(alunoList);

         AlunoService alunoService = new AlunoServiceImpl(alunoRepository);
         List<AlunoDTO> alunoDTOList = alunoService.findAll();

         Assertions.assertEquals(2,alunoDTOList.size());
         Assertions.assertEquals("Fulano",alunoDTOList.get(1).getNome());
	 }

    @Test
    public void findAlunoByMatricula() {
        AlunoRepository alunoRepository = Mockito.mock(AlunoRepository.class);
        List<Aluno> alunoList = new ArrayList<>();
        AlunoDTO alunoDTO1 = new AlunoDTO("Guilherme", "guilherme@fiap.com.br","336810");
        AlunoDTO alunoDTO2 = new AlunoDTO("Fulano", "fulano@fiap.com.br","336811");
        Aluno aluno1 = new Aluno(alunoDTO1);
        Aluno aluno2 = new Aluno(alunoDTO2);
        alunoList.add(aluno1);
        alunoList.add(aluno2);

        Mockito.when(alunoRepository.findByMatricula("336810")).thenReturn(aluno1);
        AlunoService alunoService = new AlunoServiceImpl(alunoRepository);
        AlunoDTO busca = alunoService.findByMatricula("336810");

        Assertions.assertEquals("Guilherme", busca.getNome());

    }

    @Test
    public void findAlunoByMatriculaException() {
        AlunoRepository alunoRepository = Mockito.mock(AlunoRepository.class);
        List<Aluno> alunoList = new ArrayList<>();
        AlunoDTO alunoDTO1 = new AlunoDTO("Guilherme", "guilherme@fiap.com.br","336810");
        AlunoDTO alunoDTO2 = new AlunoDTO("Fulano", "fulano@fiap.com.br","336811");
        Aluno aluno1 = new Aluno(alunoDTO1);
        Aluno aluno2 = new Aluno(alunoDTO2);
        alunoList.add(aluno1);
        alunoList.add(aluno2);

        AlunoService alunoService = new AlunoServiceImpl(alunoRepository);
        Assertions.assertThrows(ResponseStatusException.class,
                ()->{ alunoService.findByMatricula("336815");}
                );

    }

    @Test
    public void saveAluno(){
	     AlunoRepository alunoRepository = Mockito.mock(AlunoRepository.class);
	     AlunoDTO alunoDTO = new AlunoDTO("Guilherme","guilherme@fiap.com.br","336810");
	     Aluno aluno = new Aluno(alunoDTO);

        AlunoService alunoService = new AlunoServiceImpl(alunoRepository);
        Mockito.when(alunoRepository.save(Mockito.any(Aluno.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        AlunoDTO response = alunoService.saveAluno(alunoDTO);
        verify(alunoRepository, times(1)).save(Mockito.any(Aluno.class));
        Assertions.assertEquals(response.getNome(),aluno.getNome());

    }


}
