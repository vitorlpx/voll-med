package br.com.med.voll.api.repository;

import br.com.med.voll.api.persistency.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    @Query("""
            SELECT p.ativo
            FROM Paciente p
            WHERE p.id = :id
            """)
    Boolean findPacienteAtivoById(Long id);
}

