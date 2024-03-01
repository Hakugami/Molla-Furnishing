package models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.enums.UserRole;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    private Date birthday;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Column(nullable = false)
    private String job;

    @Setter
    @Column(nullable = false)
    private String interest;

    @Setter
    @Column(nullable = false , unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String salt;

    @Setter
    @Column(nullable = false)
    private double creditLimit;

    @Setter
    @Embedded
    private Address address;

    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRole role;


}