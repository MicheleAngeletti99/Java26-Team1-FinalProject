package com.example.Java26_Team1_FinalProject.repositories;

import com.example.Java26_Team1_FinalProject.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
