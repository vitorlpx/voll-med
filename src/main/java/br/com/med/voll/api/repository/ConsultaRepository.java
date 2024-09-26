package br.com.med.voll.api.repository;

import br.com.med.voll.api.persistency.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
}
