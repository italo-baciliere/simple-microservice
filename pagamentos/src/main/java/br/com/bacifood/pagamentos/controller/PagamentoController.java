package br.com.bacifood.pagamentos.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bacifood.pagamentos.dto.PagamentoDTO;
import br.com.bacifood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService service;

    @GetMapping
    public Page<PagamentoDTO> listar(@PageableDefault(size=10) Pageable paginacao){
        return service.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> detalhar(@PathVariable @NotNull Long id){
        PagamentoDTO pagamentoDTO = service.obterPorId(id);
        return ResponseEntity.ok().body(pagamentoDTO);
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> cadastrar(
        @RequestBody @Valid PagamentoDTO pagamentoDTO,
        UriComponentsBuilder uriBuilder
        ){
        PagamentoDTO pagamentoCriado = service.criarPagamento(pagamentoDTO);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoCriado.getId()).toUri();
        return ResponseEntity.created(endereco).body(pagamentoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDTO pagamentoDTO){
        PagamentoDTO atualizado = service.atualizarPagamento(id, pagamentoDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDTO> remover(@PathVariable @NotNull Long id){
        service.excluirPagamento(id);
        return ResponseEntity.noContent().build();        
    }
}