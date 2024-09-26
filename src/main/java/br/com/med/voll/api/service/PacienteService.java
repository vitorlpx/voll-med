package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.DadosAtualizacaoPacienteDTO;
import br.com.med.voll.api.dto.DadosCadastroPacienteDTO;
import br.com.med.voll.api.dto.DadosEnderecoDTO;
import br.com.med.voll.api.dto.DadosListagemPacienteDTO;
import br.com.med.voll.api.persistency.EnderecoEntity;
import br.com.med.voll.api.persistency.PacienteEntity;
import br.com.med.voll.api.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository repository;

    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        repository.save(new PacienteEntity(dados));
    }

    public Page<DadosListagemPacienteDTO> listarPacientes(@PageableDefault(size = 1, sort = {"telefone"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPacienteDTO::new);
    }

    public PacienteEntity detalharPaciente(@PathVariable(name = "id") Long id) {
        return repository.getReferenceById(id);
    }

    public void atualizarInformacoesPaciente(@RequestBody @Valid DadosAtualizacaoPacienteDTO dados) throws Exception {
        PacienteEntity paciente = repository.findById(dados.id()).orElse(null);

        if (paciente != null) {
            EnderecoEntity endereco = paciente.getEndereco();

            if (endereco == null) {
                endereco = new EnderecoEntity();
            }

            DadosEnderecoDTO dadosEndereco = dados.endereco();

            //Atribuindo os novos valores do paciente que estão sendo atualizados
            paciente.setNome(dados.nome());
            paciente.setTelefone(dados.email());

            //Atribuindo os novos valores do endereço do paciente que estão sendo atualizados
            endereco.setLogradouro(dadosEndereco.logradouro());
            endereco.setBairro(dadosEndereco.bairro());
            endereco.setNumero(dadosEndereco.numero());
            endereco.setCidade(dadosEndereco.cidade());
            endereco.setCep(dadosEndereco.cep());
            endereco.setUf(dadosEndereco.uf());
            endereco.setComplemento(dadosEndereco.complemento());

            paciente.setEndereco(endereco);

            repository.save(paciente);
        } else {
            throw new Exception("Erro ao atualizar informações do paciente!");
        }
    }

    public void excluirPaciente(@PathVariable(name = "id") Long id) {
        repository.deleteById(id);
    }

}
