package br.com.fiap.fiapbank.controller;

import br.com.fiap.fiapbank.dto.CreateCartaoDTO;
import br.com.fiap.fiapbank.dto.ExtratoDTO;
import br.com.fiap.fiapbank.entity.Transacao;
import br.com.fiap.fiapbank.service.CartaoService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService){
        this.cartaoService = cartaoService;
    }

    @PostMapping()
    public @ResponseBody
    ResponseEntity<?> saveCartao(@RequestBody CreateCartaoDTO createCartaoDTO){
        return ResponseEntity.ok().body(cartaoService.saveCartao(createCartaoDTO));
    }

    @GetMapping()
    public @ResponseBody ResponseEntity<?> listarCartoes(){
        return ResponseEntity.ok().body(cartaoService.findAll());
    }

    @GetMapping("{numeroCartao}")
    public @ResponseBody ResponseEntity<?> buscarNumeroCartao(@PathVariable String numeroCartao){
        return ResponseEntity.ok().body(cartaoService.findByNumeroCartao(numeroCartao));
    }

    @DeleteMapping("{numeroCartao}")
    public @ResponseBody ResponseEntity<?> excluirCartao(@PathVariable String numeroCartao){
        return  ResponseEntity.ok().body(cartaoService.excluircartao(numeroCartao));
    }

    @PutMapping("{numeroCartaoAtual}")
    public @ResponseBody ResponseEntity<?> atualizarCartao(@PathVariable String numeroCartaoAtual,
                                                           @RequestBody CreateCartaoDTO createCartaoDTO){
        return ResponseEntity.ok().body(cartaoService.updateCartao(numeroCartaoAtual,createCartaoDTO));
    }

    @GetMapping("alunos/{matricula}")
    public @ResponseBody ResponseEntity<?> buscarCartoesMatricula(@PathVariable String matricula){
        return ResponseEntity.ok().body(cartaoService.findByAluno(matricula));
    }

    @GetMapping("{numeroCartao}/extrato")
    public void buscarExtrato(HttpServletResponse response, @PathVariable String numeroCartao,
                              @RequestParam Long qtdDias) throws Exception{

        String filename = "extrato.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<ExtratoDTO> writer = new StatefulBeanToCsvBuilder<ExtratoDTO>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        writer.write(cartaoService.getExtrato(numeroCartao,qtdDias));
    }

}
