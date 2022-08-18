package br.com.ifsc.aither.backend.domain.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Builder;

@Builder
public class UsuarioNull implements Usuario {

	private static final long serialVersionUID = 7237017405740556756L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("NULL_ROLE"));
	}

	@Override
	public String getPassword() {
		return "NULL PASSWORD";
	}

	@Override
	public String getUsername() {
		return "NULL USERNAME";
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
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
