package it.marconi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import it.marconi.domains.Prodotti;

public interface ProdottiRepository extends JpaRepository<Prodotti, UUID>{

    
} 
