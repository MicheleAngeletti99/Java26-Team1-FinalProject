package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
import com.example.Java26_Team1_FinalProject.entities.Ente;
import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EnteRepository extends JpaRepository<Ente,Long> {
    /**
     * Effettua la cancellazione logica (o disattivazione) di un ente nel database dato l'id.
     *
     * @param id l'id dell'ente da cancellare, deve non essere null.
     */
    @Modifying // indica che il metodo va a modificare i dati del database
    @Transactional // indica che il metodo viene fatto in una transazione, se la transazione va a buon fine i cambiamenti vengono salvati nel database
    @Query(value = "update ente set is_active = false where id = :id", nativeQuery = true)
    void logicDeleteById(@Param("id") Long id);

    /**
     * Legge tutti gli enti attivi presenti nel database.
     *
     * @return una lista degli enti attivi presenti nel database.
     */
    @Query(value = "select * from ente e where e.is_active = true", nativeQuery = true)
    List<Ente> findAllActive();

    /**
     * Cerca un ente attivo nel database dato l'id.
     *
     * @param id l'id dell'ente da cercare, deve non essere null.
     * @return un Optional con l'ente in caso sia presente, altrimenti un Optional vuoto.
     */
    @Query(value = "select * from ente e where e.is_active = true and e.id = :id", nativeQuery = true)
    Optional<Ente> findActiveById(@Param("id") Long id);

    /**
     * Cerca tutti gli enti attivi nel database con un nome che contiene quello dato.
     *
     * @param nome il nome per cercare l'ente, deve non essere null.
     * @return una List degli enti trovati.
     */
    @Query(value = "select * from ente e where e.is_active = true and e.nome like %:nome%", nativeQuery = true)
    List<Ente> findActiveByNome(@Param("nome") String nome);

    /**
     * Cerca tutti gli enti attivi nel database di una specifica città.
     *
     * @param citta la città in cui si cerca l'ente, deve non essere null.
     * @return una List degli enti trovati.
     */
    @Query(value = "select * from ente e where e.is_active = true and e.citta like :citta", nativeQuery = true)
    List<Ente> findActiveByCitta(@Param("citta") String citta);
}
