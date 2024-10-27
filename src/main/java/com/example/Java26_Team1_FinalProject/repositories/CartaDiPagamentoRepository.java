package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.CartaDiPagamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartaDiPagamentoRepository extends JpaRepository<CartaDiPagamento,Long> {

    /**
     * Elimina tutte le carte di pagamento collegate ad un id cliente.
     * @param idCliente ID del cliente collegato alle carte di pagamento
     */
    @Modifying
    @Transactional
    @Query(value = "delete from carta_di_pagamento where cliente_id = :id", nativeQuery = true)
    void deleteByCliente(@Param("id") Long idCliente);

    /**
     * Cerca tutte le carte di pagamento di un cliente nel database tramite il suo id.
     *
     * @param idCliente l'id del cliente di cui si cercano le carte di pagamento, deve non essere null.
     * @return una List con le carte di pagamento del cliente.
     */
    @Query(value = "select * from carta_di_pagamento ca where ca.cliente_id = :id", nativeQuery = true)
    List<CartaDiPagamento> findByCliente(@Param("id") Long idCliente);
}
