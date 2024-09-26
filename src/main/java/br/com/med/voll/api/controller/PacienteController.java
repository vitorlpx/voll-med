package br.com.med.voll.api.controller;

import br.com.med.voll.api.dto.*;
import br.com.med.voll.api.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dados) {
        service.cadastrarPaciente(dados);

        return ResponseEntity.ok(dados);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientes(@PageableDefault(size = 1, sort = {"telefone"}) Pageable paginacao) {
        var page = service.listarPacientes(paginacao);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity detalharPaciente(@PathVariable(name = "id") Long id) {
        var paciente = service.detalharPaciente(id);

        return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarInformacoesPaciente(@RequestBody @Valid DadosAtualizacaoPacienteDTO dados) throws Exception {
        service.atualizarInformacoesPaciente(dados);

        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity excluirPaciente(@PathVariable(name = "id") Long id) {
        service.excluirPaciente(id);

        return ResponseEntity.noContent().build();
    }

}