package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Albergo;
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
public interface AlbergoRepository extends JpaRepository<Albergo,Long> {
    /**
     * Effettua la cancellazione logica (o disattivazione) di un albergo nel database dato l'id.
     *
     * @param id l'id dell'albergo da cancellare, deve non essere null.
     */
    @Modifying // indica che il metodo va a modificare i dati del database
    @Transactional // indica che il metodo viene fatto in una transazione, se la transazione va a buon fine i cambiamenti vengono salvati nel database
    @Query(value = "update albergo set is_active = false where id = :id", nativeQuery = true)
    void logicDeleteById(@Param("id") Long id);

    /**
     * Legge tutti gli alberghi attivi presenti nel database.
     *
     * @return una lista degli alberghi attivi presenti nel database.
     */
    @Query(value = "select * from albergo a where a.is_active = true", nativeQuery = true)
    List<Albergo> findAllActive();

    /**
     * Cerca un albergo attivo nel database dato l'id.
     *
     * @param id l'id dell'albergo da cercare, deve non essere null.
     * @return un Optional con l'albergo in caso sia presente, altrimenti un Optional vuoto.
     */
    @Query(value = "select * from albergo a where a.is_active = true and a.id = :id", nativeQuery = true)
    Optional<Albergo> findActiveById(@Param("id") Long id);

    /**
     * Cerca tutti gli alberghi attivi nel database con un nome che contiene quello dato.
     *
     * @param nome il nome per cercare l'albergo, deve non essere null.
     * @return una List degli alberghi trovati.
     */
    @Query(value = "select * from albergo a where a.is_active = true and a.nome like %:nome%", nativeQuery = true)
    List<Albergo> findActiveByNome(@Param("nome") String nome);

    /**
     * Cerca tutti gli alberghi attivi nel database di una specifica città.
     *
     * @param citta la città in cui si cerca l'albergo, deve non essere null.
     * @return una List degli alberghi trovati.
     */
    @Query(value = "select * from albergo a where a.is_active = true and a.citta like :citta", nativeQuery = true)
    List<Albergo> findActiveByCitta(@Param("citta") String citta);
}
