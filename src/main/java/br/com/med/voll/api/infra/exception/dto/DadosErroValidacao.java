package br.com.med.voll.api.infra.exception.dto;

import br.com.med.voll.api.dto.DadosListagemMedicoDTO;
import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String mensagem) {

    public DadosErroValidacao(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
