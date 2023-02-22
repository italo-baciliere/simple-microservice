package br.com.bacifood.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bacifood.pagamentos.model.Pagamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
    public Page<Pagamento> findAll(Pageable paginacao);                 
}