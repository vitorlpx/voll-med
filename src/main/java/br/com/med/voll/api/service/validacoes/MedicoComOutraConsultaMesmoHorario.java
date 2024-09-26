package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.exception.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoComOutraConsultaMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados) {
        var isPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if(isPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("O médico já possui outra consulta agendada nesse horário!");
        }
    }

}
