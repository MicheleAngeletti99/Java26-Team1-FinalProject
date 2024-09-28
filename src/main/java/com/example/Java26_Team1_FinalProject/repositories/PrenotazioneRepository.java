package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    @Query(value = "select * from prenotazioni p where p.is_active = true", nativeQuery = true)
    List<Prenotazione> findAllActive();

    // se non funziona metti il findById() e poi un controllo su isActive nel service
    @Query(value = "select * from prenotazioni p where p.is_active = true and p.id = :id", nativeQuery = true)
    Optional<Prenotazione> findActiveById(@Param("id") Long id);
}
