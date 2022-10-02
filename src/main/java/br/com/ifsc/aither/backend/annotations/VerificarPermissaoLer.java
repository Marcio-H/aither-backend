package br.com.ifsc.aither.backend.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("@securityService.hasPermission(#root.this.resource, T(br.com.ifsc.aither.backend.enums.DominioRecurso).LER)")
@Retention(RUNTIME)
@Target(METHOD)
public @interface VerificarPermissaoLer {

}
