package br.com.ifsc.aither.backend.config;

import br.com.ifsc.aither.backend.component.CustomAuthenticationFilter;
import br.com.ifsc.aither.backend.component.CustomAuthorizationFilter;
import br.com.ifsc.aither.backend.component.TokenFactory;
import br.com.ifsc.aither.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UsuarioService usuarioService;
	private final TokenFactory tokenFactory;
	private final AuthenticationConfiguration authenticationConfiguration;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfigurationSource());
		http.csrf().disable();
		http.authorizeHttpRequests().anyRequest().permitAll();

		var authenticationManager = authenticationConfiguration.getAuthenticationManager();
		var customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager, tokenFactory);

		customAuthenticationFilter.setFilterProcessesUrl("/login");
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore(new CustomAuthorizationFilter(tokenFactory, usuarioService), UsernamePasswordAuthenticationFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		configuration.setAllowedMethods(List.of(
				HttpMethod.GET.name(),
				HttpMethod.PUT.name(),
				HttpMethod.POST.name(),
				HttpMethod.DELETE.name()
		));
		source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
		return source;
	}
}
