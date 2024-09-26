package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.exception.ValidacaoException;
import br.com.med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados) {
        var isPacienteAtivo = repository.findPacienteAtivoById(dados.idPaciente());

        if(!isPacienteAtivo) {
            throw new ValidacaoException("A consulta não pode ser agendada com um paciente excluído!");
        }
    }
}
