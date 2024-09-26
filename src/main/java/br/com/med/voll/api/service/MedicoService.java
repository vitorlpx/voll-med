package br.com.med.voll.api.service;

import br.com.med.voll.api.dto.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.dto.DadosCadastroMedicoDTO;
import br.com.med.voll.api.dto.DadosEnderecoDTO;
import br.com.med.voll.api.dto.DadosListagemMedicoDTO;
import br.com.med.voll.api.persistency.EnderecoEntity;
import br.com.med.voll.api.persistency.MedicoEntity;
import br.com.med.voll.api.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MedicoService {

    @Autowired
    MedicoRepository repository;

    public MedicoEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados) throws Exception{
        if(dados != null) {
            repository.save(new MedicoEntity(dados));
        } else {
            throw new Exception("Erro ao cadastrar um médico");
        }
        return null;
    }

    public Page<DadosListagemMedicoDTO> listarMedicos(@PageableDefault(size = 3, sort = {"email"}) Pageable paginacao) throws Exception {
        if(paginacao.isPaged()) {
            return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
        } else {
            throw new Exception("Erro ao listar médicos!");
        }
    }

    public MedicoEntity detalharMedico(@PathVariable(name = "id") Long id) throws Exception {
        return repository.getReferenceById(id);
    }

    public void atualizarInformacoesMedico(DadosAtualizacaoMedicoDTO dados) throws Exception {
        MedicoEntity medico = repository.findById(dados.id()).orElse(null);

        if (medico != null) {
            EnderecoEntity endereco = medico.getEndereco();
            if (endereco == null) {
                endereco = new EnderecoEntity();
            }

            DadosEnderecoDTO dtoEndereco = dados.endereco();

            //Atribuindo os novos valores do medico que estão sendo atualizados
            medico.setNome(dados.nome());
            medico.setTelefone(dados.telefone());

            //Atribuindo os novos valores do endereço do medico que estão sendo atualizados
            endereco.setLogradouro(dtoEndereco.logradouro());
            endereco.setBairro(dtoEndereco.bairro());
            endereco.setNumero(dtoEndereco.numero());
            endereco.setCidade(dtoEndereco.cidade());
            endereco.setCep(dtoEndereco.cep());
            endereco.setUf(dtoEndereco.uf());
            endereco.setComplemento(dtoEndereco.complemento());

            medico.setEndereco(endereco);

            repository.save(medico);
        } else {
            throw new Exception("Erro ao atualizar informações do médico!");
        }
    }

    public void desativarMedico(Long id) throws Exception {
        //Exclusão lógica, não excluímos o médico do banco, mas desativamos ele
        MedicoEntity medico = repository.getReferenceById(id);

        if(medico.getAtivo() != null) {
            medico.setAtivo(false);
        } else {
            throw new Exception("Erro ao desativar médico!");
        }
    }

    public void excluirMedico(Long id) throws Exception {
        //Exclusão do médico do banco de dados
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new Exception("Erro ao fazer a exclusão do médico!");
        }
    }

}
