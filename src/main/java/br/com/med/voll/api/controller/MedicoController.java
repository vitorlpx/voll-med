package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.DadosAtualizacaoMedicoDTO;
import br.com.med.voll.api.dto.DadosCadastroMedicoDTO;
import br.com.med.voll.api.dto.DadosDetalhamentoMedicoDTO;
import br.com.med.voll.api.dto.DadosListagemMedicoDTO;
import br.com.med.voll.api.persistency.MedicoEntity;
import br.com.med.voll.api.service.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dados, UriComponentsBuilder uriBuilder) throws Exception {
        MedicoEntity medico = service.cadastrarMedico(dados);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicos(@PageableDefault(size = 3, sort = {"email"}) Pageable paginacao) throws Exception {
        var page = service.listarMedicos(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity detalharMedico(@PathVariable(name = "id") Long id) throws Exception {
        var medico = service.detalharMedico(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarInformacoesMedico(@RequestBody @Valid DadosAtualizacaoMedicoDTO dados) throws Exception {
        service.atualizarInformacoesMedico(dados);

        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity desativarMedico(@PathVariable(name = "id") Long id) throws Exception {
        service.desativarMedico(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable(name = "id") Long id) throws Exception {
        service.excluirMedico(id);

        return ResponseEntity.noContent().build();
    }

}
