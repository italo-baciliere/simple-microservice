package br.com.bacifood.pagamentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bacifood.pagamentos.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{
    
}