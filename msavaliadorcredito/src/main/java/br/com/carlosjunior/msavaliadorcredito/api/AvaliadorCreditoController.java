package br.com.carlosjunior.msavaliadorcredito.api;

import br.com.carlosjunior.msavaliadorcredito.domain.dto.DadosAvaliacaoDTO;
import br.com.carlosjunior.msavaliadorcredito.domain.dto.RetornoAvaliacaoClienteDTO;
import br.com.carlosjunior.msavaliadorcredito.domain.dto.SituacaoClienteDTO;
import br.com.carlosjunior.msavaliadorcredito.domain.service.AvaliadorCreditoService;
import br.com.carlosjunior.msavaliadorcredito.exception.ErrorComunicationMicroservicesException;
import br.com.carlosjunior.msavaliadorcredito.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping("/status")
    public String status(){
        return "Ok";
    }

    @GetMapping("/situacao-cliente")
    public ResponseEntity consultarSituacaoCliente(@RequestParam String cpf) {
        try {
            SituacaoClienteDTO situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity dadosAvaliacao(@RequestBody DadosAvaliacaoDTO dto){
        try {
            RetornoAvaliacaoClienteDTO retorno = avaliadorCreditoService.realizarAvaliacao(dto.getCpf(), dto.getRenda());
            return ResponseEntity.ok(retorno);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }


}
