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

    /**
     * Cerca tutte le prenotazioni attive nel database in relazione con uno specifico cliente.
     *
     * @param idCliente l'id del cliente di cui si cercano le prenotazioni, deve non essere null.
     * @return una List delle prenotazioni del cliente.
     */
    @Query(value = "select * from prenotazioni p where p.is_active = true and p.id_cliente = :id", nativeQuery = true)
    List<Prenotazione> findActiveByCliente(@Param("id") Long idCliente);

    /**
     * Cerca tutte le prenotazioni attive nel database in relazione con uno specifico albergo.
     *
     * @param idAlbergo l'id dell'albergo di cui si cercano le prenotazioni, deve non essere null.
     * @return una List delle prenotazioni dell'albergo.
     */
    @Query(value = "select * from prenotazioni p where p.is_active = true and p.id_albergo = :id", nativeQuery = true)
    List<Prenotazione> findActiveByAlbergo(@Param("id") Long idAlbergo);

    /**
     * Cerca tutte le prenotazioni attive nel database in relazione con uno specifico ente.
     *
     * @param idEnte l'id dell'ente di cui si cercano le prenotazioni, deve non essere null.
     * @return una List delle prenotazioni dell'ente.
     */
    @Query(value = "select * from prenotazioni p where p.is_active = true and p.id_ente = :id", nativeQuery = true)
    List<Prenotazione> findActiveByEnte(@Param("id") Long idEnte);

    /**
     * Calcola quanto un cliente ha speso tramite l'applicazione.
     *
     * @param idCliente l'id del cliente di cui si vuole trovare la spesa totale, deve non essere null.
     * @return un Double con la spesa totale.
     */
    @Query(value = "select sum(p.costo_totale) from prenotazioni p where p.is_payd = true and p.id_cliente = :id", nativeQuery = true)
    Double spesaTotaleCliente(@Param("id") Long idCliente);

    /**
     * Calcola quanto un albergo ha guadagnato tramite l'applicazione.
     *
     * @param idAlbergo l'id dell'albergo di cui si vuole trovare il guadagno totale, deve non essere null.
     * @return un Double con il guadagno totale.
     */
    @Query(value = "select sum(p.costo_totale) from prenotazioni p where p.is_payd = true and p.id_albergo = :id", nativeQuery = true)
    Double guadagnoTotaleAlbergo(@Param("id") Long idAlbergo);

    /**
     * Calcola quanto un ente ha guadagnato tramite l'applicazione.
     *
     * @param idEnte l'id dell'ente di cui si vuole trovare il guadagno totale, deve non essere null.
     * @return un Double con il guadagno totale.
     */
    @Query(value = "select sum(p.costo_totale) from prenotazioni p where p.is_payd = true and p.id_ente = :id", nativeQuery = true)
    Double guadagnoTotaleEnte(@Param("id") Long idEnte);
}
