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

    // custom query per AGGIUNGERE un relazione tra un prenotazione e un servizio tramite la terza tabella prenotazione_servizi
    @Transactional
    @Modifying
    @Query(value = "insert into prenotazioni_servizi(id_prenotazione, id_servizio) values(:idPrenotazione,:idServizio)", nativeQuery = true)
    void createRelationPrenotazioneEServizio(@Param("idPrenotazione") Long idPrenotazione, @Param("idServizio") Long idServizio);

    // custom query per RIMUOVERE un relazione tra un prenotazione e un servizio tramite la terza tabella prenotazione_servizi
    @Transactional
    @Modifying
    @Query(value = "delete from prenotazioni_servizi where id_prenotazione = :idPrenotazione AND id_servizio = :idServizio", nativeQuery = true)
    void deleteRelationPrenotazioneEServizio(@Param("idPrenotazione") Long idPrenotazione, @Param("idServizio") Long idServizio);

    /**
     * Cerca tutti i servizi attivi nel database il cui nome contiene quello dato.
     *
     * @param nome il nome per cercare i servizi.
     * @return una List dei servizi trovati.
     */
    @Query(value = "select * from servizi s where s.is_active = true and s.name like %:nome%", nativeQuery = true)
    List<Servizi> findByNome(@Param("nome") String nome);

    /**
     * Cerca tutti i servizi di una prenotazione dato l'id, anche quelli eliminati (disattivati).
     *
     * @param idPrenotazione l'id della prenotazione di cui si cercano i servizi, deve non essere null.
     * @return una List dei servizi della prenotazione.
     */
    @Query(value = "select s.* from servizi s join prenotazioni_servizi ps on s.id = ps.id_servizio where ps.id_prenotazione = :id", nativeQuery = true)
    List<Servizi> findByPrenotazione(@Param("id") Long idPrenotazione);

    /**
     * Cerca tutti i servizi di un albergo dato l'id.
     *
     * @param idAlbergo l'id dell'albergo di cui si cercano i servizi, deve non essere null.
     * @return una List dei servizi dell'albergo.
     */
    @Query(value = "select * from servizi s where s.is_active = true and s.id_albergo = :id", nativeQuery = true)
    List<Servizi> findByAlbergo(@Param("id") Long idAlbergo);

    /**
     * Cerca tutti i servizi di un ente dato l'id.
     *
     * @param idEnte l'id dell'ente di cui si cercano i servizi, deve non essere null.
     * @return una List dei servizi dell'ente.
     */
    @Query(value = "select * from servizi s where s.is_active = true and s.id_ente = :id", nativeQuery = true)
    List<Servizi> findByEnte(@Param("id") Long idEnte);
}
