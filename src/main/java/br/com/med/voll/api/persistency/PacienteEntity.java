package br.com.med.voll.api.persistency;

import br.com.med.voll.api.dto.DadosCadastroPacienteDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Boolean ativo; // Adicione este campo

    @Embedded
    private EnderecoEntity endereco;

    public PacienteEntity(DadosCadastroPacienteDTO dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new EnderecoEntity(dados.endereco());
    }

}
