package br.com.bacifood.pagamentos.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.bacifood.pagamentos.dto.PagamentoDTO;
import br.com.bacifood.pagamentos.repository.PagamentoRepository;
import br.com.bacifood.pagamentos.model.Pagamento;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository repository;

    // model mapping --> transferir dados de forma facilitade entre a entidade e o DTO
    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDTO> obterTodos(Pageable pageable){
        return repository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, PagamentoDTO.class));
    }

    public PagamentoDTO obterPorId(Long id){
        Pagamento pagamento = repository.findById(id);
        PagamentoDTO pagamentoDTO = modelMapper.map(repository.findById(id), PagamentoDTO.class);
        return pagamentoDTO;
    }
}