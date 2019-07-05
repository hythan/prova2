package com.utfpr.prova.model;


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

    private String password;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "electronic_signature")
    private String electronicSignature;


}
