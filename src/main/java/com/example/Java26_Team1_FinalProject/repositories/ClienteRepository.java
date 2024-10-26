package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Cliente;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Effettua la cancellazione logica (o disattivazione) di un cliente nel database dato l'id.
     *
     * @param id l'id del cliente da cancellare, deve non essere null.
     */
    @Modifying // indica che il metodo va a modificare i dati del database
    @Transactional
    // indica che il metodo viene fatto in una transazione, se la transazione va a buon fine i cambiamenti vengono salvati nel database
    @Query(value = "update clienti set is_active = false where id = :id", nativeQuery = true)
    void logicDeleteById(@Param("id") Long id);

    /**
     * Legge tutti i clienti attivi presenti nel database.
     *
     * @return una lista di clienti attivi presenti nel database.
     */
    @Query(value = "select * from clienti c where c.is_active = true", nativeQuery = true)
    List<Cliente> findAllActive();

    /**
     * Cerca un cliente attivo nel database dato l'id.
     *
     * @param id l'id del cliente da cercare, deve non essere null.
     * @return un Optional con il cliente in caso sia presente, altrimenti un Optional vuoto.
     */
    @Query(value = "select * from clienti c where c.is_active = true and c.id = :id", nativeQuery = true)
    Optional<Cliente> findActiveById(@Param("id") Long id);
}
