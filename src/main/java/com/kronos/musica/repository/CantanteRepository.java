package com.kronos.musica.repository;

import com.kronos.musica.model.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CantanteRepository extends JpaRepository<Cantante, Long> {

    @Query("SELECT c FROM Cantante c WHERE c.nombre ILIKE %:nombre%")
    Optional<Cantante> buscarPorNombre(String nombre);
}
