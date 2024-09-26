package br.com.med.voll.api.dto;

import br.com.med.voll.api.persistency.ConsultaEntity;

import java.time.LocalDateTime;

//DTO de resposta
public record DadosDetalhamentoConsultaDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
) {
    public DadosDetalhamentoConsultaDTO(ConsultaEntity consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
