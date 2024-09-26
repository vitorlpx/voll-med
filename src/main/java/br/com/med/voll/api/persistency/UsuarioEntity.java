package br.com.med.voll.api.persistency;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //se a aplicação tiver controle de autorização, podemos configurar a partir desse método
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        //retorna o atributo da senha
        return senha.trim();
    }

    @Override
    public String getUsername() {
        //retorna o atributo do usuario
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        //validação se caso a conta do usuário expirou
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //validação se caso a conta do usuário bloqueou
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //validação se caso a senha do usuário expirou
        return true;
    }

    @Override
    public boolean isEnabled() {
        //validação se caso a conta do usuário é válida
        return true;
    }
}
