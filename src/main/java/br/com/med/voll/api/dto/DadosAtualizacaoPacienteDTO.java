package br.com.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPacienteDTO(

        @NotNull
        Long id,
        String nome,
        String email,
        DadosEnderecoDTO endereco
) {
}
