package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var isDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isHorarioClicinaAberta = dataConsulta.getHour() < 7;
        var isHorarioClicaEncerramento = dataConsulta.getHour() > 18;

        if(isDomingo || isHorarioClicinaAberta || isHorarioClicaEncerramento) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }

}
