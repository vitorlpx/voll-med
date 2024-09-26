package br.com.med.voll.api.dto;

import br.com.med.voll.api.persistency.PacienteEntity;

public record DadosListagemPacienteDTO(Long id, String nome, String email, String telefone) {

    public DadosListagemPacienteDTO(PacienteEntity paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }

}
