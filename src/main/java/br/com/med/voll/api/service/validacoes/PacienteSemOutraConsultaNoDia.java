package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.exception.ValidacaoException;
import br.com.med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);

        var isPacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (isPacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("O paciente já possui uma consulta agendade neste dia!");
        }
    }

}
