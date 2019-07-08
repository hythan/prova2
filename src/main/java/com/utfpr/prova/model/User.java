package com.utfpr.prova.model;


import com.utfpr.prova.security.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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

    private Date created;
    private Date updated;

    public User(String userName, String email, String password, ProfileEnum roleName, String electronicSignature) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        this.electronicSignature = electronicSignature;
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = new Date();
    }

    @PrePersist
    public void onSave() {
        final Date now = new Date();
        this.created = now;
        this.updated = now;
    }


}
