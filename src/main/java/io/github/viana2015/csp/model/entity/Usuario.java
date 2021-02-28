package io.github.viana2015.csp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty(message = "{campo.usuario.obrigatorio}")
	private String username;
	
	@NotEmpty(message = "{campo.password.obrigatorio}")
	private String password;
	
	//private Boolean loginError;

}
