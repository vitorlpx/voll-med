package br.com.med.voll.api.usuario.repository;

import br.com.med.voll.api.usuario.persistency.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UserDetails findByLogin(String username);

}
