package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.DadosAgendamentoConsultaDTO;
import br.com.med.voll.api.dto.DadosCancelamentoConsultaDTO;
import br.com.med.voll.api.dto.DadosDetalhamentoConsultaDTO;
import br.com.med.voll.api.exception.ValidacaoException;
import br.com.med.voll.api.persistency.ConsultaEntity;
import br.com.med.voll.api.persistency.MedicoEntity;
import br.com.med.voll.api.repository.ConsultaRepository;
import br.com.med.voll.api.repository.MedicoRepository;
import br.com.med.voll.api.repository.PacienteRepository;
import br.com.med.voll.api.service.validacoes.ValidadorAgendamentoDeConsulta;
import br.com.med.voll.api.service.validacoes.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsultaDTO agendar(DadosAgendamentoConsultaDTO dados) {

        if(dados.idPaciente() != null && !pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("ID do paciente não existente!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID do médico não existente!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if(medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new ConsultaEntity(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsultaDTO(consulta);
    }

    public void cancelar(DadosCancelamentoConsultaDTO dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private MedicoEntity escolherMedico(DadosAgendamentoConsultaDTO dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatório quando o médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorio(dados.especialidade(), dados.data());
    }

}
