package br.com.med.voll.api.repository;

import br.com.med.voll.api.dto.DadosCadastroMedicoDTO;
import br.com.med.voll.api.dto.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.DadosEnderecoDTO;
import br.com.med.voll.api.enums.Especialidade;
import br.com.med.voll.api.persistency.ConsultaEntity;
import br.com.med.voll.api.persistency.MedicoEntity;
import br.com.med.voll.api.persistency.PacienteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;

import static java.time.temporal.TemporalAdjuster.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver nulo quando único médico cadastrado não está disponível na data.")
    void escolherMedicoAleatorioCenario1() {
        //basicamente temos que fazer a chamada para o banco, para conseguirmos testarmos.
        var proximaSegundaAsDez = LocalDate.now().with(TemporalAdjuster.next(DayOfWeek.MONDAY)).atTime(10, 0);

        //em cenário de testes, toda vez precisamos cadastrar as informações que já estão previamente cadastradas.
        var medico =


        var medicoLivre = repository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
        
        assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(MedicoEntity medico, PacienteEntity paciente, LocalDateTime data) {
        em.persist(new ConsultaEntity(null, medico, paciente, data));
    }

    private MedicoEntity cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new MedicoEntity(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private PacienteEntity cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new PacienteEntity(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEnderecoDTO dadosEndereco() {
        return new DadosEnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}