package br.com.fiap.fiapbank;

import br.com.fiap.fiapbank.dto.AlunoDTO;
import br.com.fiap.fiapbank.entity.Aluno;
import br.com.fiap.fiapbank.repository.AlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:database")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class FiapBankIntegrationsTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void PostAluno() throws Exception {

        mockMvc.perform( MockMvcRequestBuilders
                .post("/alunos")
                .content(asJsonString(new AlunoDTO( "Guilherme", "email4@mail.com", "336811")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").exists());

        mockMvc.perform( MockMvcRequestBuilders
                .post("/alunos")
                .content(asJsonString(new AlunoDTO( "Guilherme", "email4@mail.com", "336811")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void GetAlunos() throws Exception{

        mockMvc.perform( MockMvcRequestBuilders
                .post("/alunos")
                .content(asJsonString(new AlunoDTO( "Guilherme", "email4@mail.com", "336810")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform( MockMvcRequestBuilders
                .post("/alunos")
                .content(asJsonString(new AlunoDTO( "Fulano", "email4@mail.com", "336811")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform( MockMvcRequestBuilders
                .get("/alunos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].matricula").isNotEmpty());
    }

    @Test
    void GetAlunoByMatricula() throws Exception{

        mockMvc.perform( MockMvcRequestBuilders
                .post("/alunos")
                .content(asJsonString(new AlunoDTO( "Guilherme", "email4@mail.com", "336810")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));


        mockMvc.perform( MockMvcRequestBuilders
                .get("/alunos/{matricula}", "336810")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Guilherme"));
    }


    @Test
    public void DeleteAluno() throws Exception
    {

        mockMvc.perform( MockMvcRequestBuilders
                .post("/alunos")
                .content(asJsonString(new AlunoDTO( "Guilherme", "email4@mail.com", "336810")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform( MockMvcRequestBuilders.delete("/alunos/{matricula}", "336810") )
                .andExpect(status().isOk());
    }

    @Test
    public void PutAluno() throws Exception
    {

        mockMvc.perform( MockMvcRequestBuilders
                .post("/alunos")
                .content(asJsonString(new AlunoDTO( "Guilherme", "email4@mail.com", "336810")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform( MockMvcRequestBuilders
                .put("/alunos/{matriculaAtual}", "336810")
                .content(asJsonString(new AlunoDTO( "Fulano", "email3@mail.com", "336810")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Fulano"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email3@mail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value("336810"));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
