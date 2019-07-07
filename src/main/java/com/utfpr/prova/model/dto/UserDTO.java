package com.utfpr.prova.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private Long id;

    @Pattern(regexp = "^(\\s?[A-ZÀ-Ú][a-zà-ú]+)+(\\s[a-zà-ú]*)?(\\s[A-ZÀ-Ú][a-zà-ú]*)+", message = "Insira o seu nome completo iniciando com letras maíusculas.")
    @NotEmpty(message = "Por favor ensira o nome do usuário.")
    private String userName;

    @NotEmpty(message = "Por favor ensira uma senha.")
    private String password;

    private String roleName;

    @NotEmpty(message = "Por favor ensira uma assinatura eletronica.")
    private String electronicSignature;

}
