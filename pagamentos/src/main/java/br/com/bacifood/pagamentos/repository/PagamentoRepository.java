package br.com.bacifood.pagamentos.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bacifood.pagamentos.dto.PagamentoDTO;
import br.com.bacifood.pagamentos.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{

    Page<PagamentoDTO> findAll(Pageable paginacao);
    
}