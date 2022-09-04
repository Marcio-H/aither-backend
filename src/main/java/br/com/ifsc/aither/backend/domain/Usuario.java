package br.com.ifsc.aither.backend.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

import static br.com.ifsc.aither.backend.utils.Encoder.encode;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 2228836561898093412L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Email
	@Column(unique = true)
	private String username;

	@NotNull
	@Column
	private String password;

	@NotBlank
	@Column
	private String name;

	@Builder.Default
	@Column
	private boolean enabled = true;

	public void setPassword(@NonNull String password) {
		this.password = encode(password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public static Usuario usuarioNull() {
		return Usuario.builder()
				.id(-1)
				.name("USUÁRIO NULO")
				.username("usuario.nulo@nulo.com")
				.password("SENHA NULA")
				.build();
	}

	public static class UsuarioBuilder {

		public UsuarioBuilder password(@NonNull String password) {
			this.password = encode(password);
			return this;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		if (!(obj instanceof Red)) {
			return false;
		}

		Usuario other = (Usuario) obj;
		return id != null && id.equals(other.getId());
	}
}
