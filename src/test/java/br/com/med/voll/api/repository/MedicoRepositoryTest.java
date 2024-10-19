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
import java.time.temporal.TemporalAdjusters;

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
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        //Essa linha calcula a próxima segunda-feira às 10h da manhã, usando as classes LocalDate e TemporalAdjusters do Java.
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente(true, "Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        var medicoLivre = repository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //Essa linha usa a biblioteca AssertJ para verificar se o valor de medicoLivre é nulo. Se for nulo, o teste passa.
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        var medicoLivre = repository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(MedicoEntity medico, PacienteEntity paciente, LocalDateTime data) {
        em.persist(new ConsultaEntity(123L, medico, paciente, data));
    }

    private MedicoEntity cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new MedicoEntity(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private PacienteEntity cadastrarPaciente(Boolean ativo, String nome, String email, String cpf) {
        var paciente = new PacienteEntity(dadosPaciente(ativo, nome, email, cpf));
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

    private DadosCadastroPacienteDTO dadosPaciente(Boolean ativo, String nome, String email, String cpf) {
        return new DadosCadastroPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco(),
                ativo
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