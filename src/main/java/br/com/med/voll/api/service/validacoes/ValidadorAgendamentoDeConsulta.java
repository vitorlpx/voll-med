package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsultaDTO dados);

}
