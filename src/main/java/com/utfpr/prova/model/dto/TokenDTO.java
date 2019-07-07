package com.utfpr.prova.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * DTO para enviar o token ao cliente.
 * @author ronifabio
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
	
	private String token;
}
