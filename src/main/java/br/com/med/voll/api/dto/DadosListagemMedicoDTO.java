package br.com.med.voll.api.dto;

import br.com.med.voll.api.persistency.MedicoEntity;
import br.com.med.voll.api.enums.Especialidade;

public record DadosListagemMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicoDTO (MedicoEntity medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
