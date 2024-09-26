package br.com.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDTO endereco
) {
}
