package com.utfpr.prova.model;


import com.utfpr.prova.security.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_name",
            unique = true)
    private String userName;

    @Column(name = "email",
            unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private ProfileEnum roleName;

    @Column(name = "electronic_signature")
    private String electronicSignature;


}
