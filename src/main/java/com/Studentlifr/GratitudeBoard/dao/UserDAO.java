package com.Studentlifr.GratitudeBoard.dao;

import com.Studentlifr.GratitudeBoard.enumerators.ROLES;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "emailId",nullable = false, unique = true)
    private String emailId;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ROLES role = ROLES.USER;
    @Column(name = "isVerified",nullable = false)
    @Builder.Default
    private Boolean isVerified= false;


}
