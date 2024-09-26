package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosCancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsultaDTO dados);

}
