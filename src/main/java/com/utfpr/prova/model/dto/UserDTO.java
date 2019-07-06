package com.utfpr.prova.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private Long id;

    @NotEmpty(message = "Por favor ensira o nome do usuário.")
    private String userName;

    @NotEmpty(message = "Por favor ensira uma senha.")
    private String password;

    private String roleName;

    @NotEmpty(message = "Por favor ensira uma assinatura eletronica.")
    private String electronicSignature;

}
