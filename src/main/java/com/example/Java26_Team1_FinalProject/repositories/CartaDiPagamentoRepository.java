package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.CartaDiPagamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartaDiPagamentoRepository extends JpaRepository<CartaDiPagamento,Long> {

    /**
     * Elimina tutte le carte di pagamento collegate ad un id cliente.
     * @param idCliente ID del cliente collegato alle carte di pagamento
     */
    @Modifying
    @Transactional
    @Query(value = "delete from carta_di_pagamento where cliente_id = :id", nativeQuery = true)
    void deleteByCliente(@Param("id") Long idCliente);

}
