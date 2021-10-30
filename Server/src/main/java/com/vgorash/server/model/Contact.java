package com.vgorash.server.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "rdba_contact")
@Entity
public class Contact {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "user_id", nullable = false)
    private String userId;
}