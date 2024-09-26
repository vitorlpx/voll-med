package br.com.med.voll.api.dto;

import br.com.med.voll.api.persistency.EnderecoEntity;
import br.com.med.voll.api.persistency.MedicoEntity;
import br.com.med.voll.api.enums.Especialidade;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, EnderecoEntity endereco) {
    public DadosDetalhamentoMedicoDTO (MedicoEntity medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());

    }
}
