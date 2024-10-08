package com.example.Java26_Team1_FinalProject.repositories;

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
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    /**
     * Effettua la cancellazione logica (o disattivazione) di una prenotazione nel database dato l'id.
     *
     * @param id l'id della prenotazione da cancellare, deve non essere null.
     */
    @Modifying // indica che il metodo va a modificare i dati del database
    @Transactional // indica che il metodo viene fatto in una transazione, se la transazione va a buon fine i cambiamenti vengono salvati nel database
    @Query(value = "update prenotazioni set is_active = false where id = :id", nativeQuery = true)
    void logicDeleteById(@Param("id") Long id);

    /**
     * Legge tutte le prenotazioni attive presenti nel database.
     *
     * @return una lista delle perenotazioni attive presenti nel database.
     */
    @Query(value = "select * from prenotazioni p where p.is_active = true", nativeQuery = true)
    List<Prenotazione> findAllActive();

    /**
     * Cerca una prenotazione attiva nel database dato l'id.
     *
     * @param id l'id della prenotazione da cercare, deve non essere null.
     * @return un Optional con la prenotazione in caso sia presente, altrimenti un Optional vuoto.
     */
    @Query(value = "select * from prenotazioni p where p.is_active = true and p.id = :id", nativeQuery = true)
    Optional<Prenotazione> findActiveById(@Param("id") Long id);
}
