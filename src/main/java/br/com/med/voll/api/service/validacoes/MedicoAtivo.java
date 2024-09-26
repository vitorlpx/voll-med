package br.com.med.voll.api.service.validacoes;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.exception.ValidacaoException;
import br.com.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class MedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsultaDTO dados)  {
        //Escolha do médico opcional
        if(dados.idMedico() == null) {
            return;
        }

        var isMedicoAtivo = repository.findMedicoAtivoById(dados.idMedico());
        if(!isMedicoAtivo) {
            throw new ValidacaoException("O médico não está disponível para essa consulta!");
        }
    }

}
