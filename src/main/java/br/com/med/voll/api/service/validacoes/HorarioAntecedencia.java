package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30) {
            throw new ValidacaoException("A consulta deve ser agendeda com 30 minutos de antecedência!");
        }
    }

}
