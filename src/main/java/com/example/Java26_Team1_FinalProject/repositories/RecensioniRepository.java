package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecensioniRepository extends JpaRepository<Recensione, Long> {

    /**
     * Cerca la recensione collegata ad una pernotazione data tramite l'id.
     *
     * @param idPrenotazione l'id della prenotazione di cui si cerca la recensione, deve non essere null.
     * @return un Optional contenente la recensione della prenotazione, un Optional vuoto se non è stata trovata.
     */
    @Query(value = "select * from recensione r where r.id_prenotazione = :id", nativeQuery = true)
    Optional<Recensione> findByPrenotazione(@Param("id") Long idPrenotazione);

    /**
     * Cerca tutte le recensioni scritte da un cliente dato l'id.
     *
     * @param idCliente l'id del cliente di cui si cercano le recensioni, deve non essere null.
     * @return una List delle recensioni scritte dal cliente.
     */
    @Query(value = "select r.* from recensione r join prenotazioni p on r.id_prenotazione = p.id where p.id_cliente = :id", nativeQuery = true)
    List<Recensione> findByCliente(@Param("id") Long idCliente);

    /**
     * Cerca tutte le recensioni ricevute da un albergo dato l'id.
     *
     * @param idAlbergo l'id dell'albergo di cui si cercano le recensioni, deve non essere null.
     * @return una List delle recensioni ricevute dall'albergo.
     */
    @Query(value = "select r.* from recensione r join prenotazioni p on r.id_prenotazione = p.id where p.id_albergo = :id", nativeQuery = true)
    List<Recensione> findByAlbergo(@Param("id") Long idAlbergo);

    /**
     * Cerca tutte le recensioni ricevute da un ente dato l'id.
     *
     * @param idEnte l'id dell'ente di cui si cercano le recensioni, deve non essere null.
     * @return una List delle recensioni ricevute dall'ente.
     */
    @Query(value = "select r.* from recensione r join prenotazioni p on r.id_prenotazione = p.id where p.id_ente = :id", nativeQuery = true)
    List<Recensione> findByEnte(@Param("id") Long idEnte);
}
