package br.com.med.voll.api.dto;

import br.com.med.voll.api.persistency.EnderecoEntity;
import br.com.med.voll.api.persistency.PacienteEntity;

public record DadosDetalhamentoPacienteDTO(Long id, String nome, String email, String telefone, String cpf, EnderecoEntity endereco) {
    public DadosDetalhamentoPacienteDTO(PacienteEntity paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
