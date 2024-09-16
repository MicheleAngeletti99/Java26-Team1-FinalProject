package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
