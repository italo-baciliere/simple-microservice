package br.com.bacifood.pagamentos.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bacifood.pagamentos.dto.PagamentoDTO;
import br.com.bacifood.pagamentos.httpClient.PedidoClient;
import br.com.bacifood.pagamentos.model.Pagamento;
import br.com.bacifood.pagamentos.model.Status;
import br.com.bacifood.pagamentos.repository.PagamentoRepository;

import java.util.Optional;

// import jakarta.persistence.EntityNotFoundException;
import javax.persistence.EntityNotFoundException;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoClient pedido;

    public Page<PagamentoDTO> obterTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDTO.class));
    }

    public PagamentoDTO obterPorId(Long id){
        Pagamento pagamento = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO criarPagamento(PagamentoDTO dto){                   
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO updatePagamento(Long id, PagamentoDTO dto){
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }

    public void confirmarPagamento(Long id){
        Optional<Pagamento> pagamento = repository.findById(id);
        if(!pagamento.isPresent())
            throw new EntityNotFoundException();
        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        pedido.atualizaPagamento(pagamento.get().getId()); // PAGO
    }
}