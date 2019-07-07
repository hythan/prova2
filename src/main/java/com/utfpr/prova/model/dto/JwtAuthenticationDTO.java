package com.utfpr.prova.model.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * DTO para receber os dados de login.
 * @author ronifabio
 *
 */
@Data
@NoArgsConstructor
public class JwtAuthenticationDTO {
	
	@NotEmpty(message = "O email não pode ser vazio.")
	private String email;
	
	@NotEmpty(message = "A senha não pode ser vazia.")
	private String password;

}
