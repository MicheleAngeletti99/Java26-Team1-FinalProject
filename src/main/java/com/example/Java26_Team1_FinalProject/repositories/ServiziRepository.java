package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import com.example.Java26_Team1_FinalProject.entities.Servizi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiziRepository extends JpaRepository<Servizi, Long> {
    /**
     * Effettua la cancellazione logica (o disattivazione) di un servizio nel database dato l'id.
     *
     * @param id l'id del servizio da cancellare, deve non essere null.
     */
    @Modifying // indica che il metodo va a modificare i dati del database
    @Transactional // indica che il metodo viene fatto in una transazione, se la transazione va a buon fine i cambiamenti vengono salvati nel database
    @Query(value = "update servizi set is_active = false where id = :id", nativeQuery = true)
    void logicDeleteById(@Param("id") Long id);

    /**
     * Legge tutti i servizi attivi presenti nel database.
     *
     * @return una lista dei servizi attivi presenti nel database.
     */
    @Query(value = "select * from servizi s where s.is_active = true", nativeQuery = true)
    List<Servizi> findAllActive();

    /**
     * Cerca un servizio attivo nel database dato l'id.
     *
     * @param id l'id del servizio da cercare, deve non essere null.
     * @return un Optional con il servizio in caso sia presente, altrimenti un Optional vuoto.
     */
    @Query(value = "select * from servizi s where s.is_active = true and s.id = :id", nativeQuery = true)
    Optional<Servizi> findActiveById(@Param("id") Long id);
}
