package br.com.med.voll.api.dto;

import br.com.med.voll.api.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultaDTO(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo
) {
}
