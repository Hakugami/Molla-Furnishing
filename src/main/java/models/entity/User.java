package models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.enums.UserRole;
import java.util.Date;
import java.util.List;

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
    private String phone;

    @Setter
    @Column(nullable = false)
    private String gender;

    @Setter
    @Column(nullable = false)
    private String salt;

    @Setter
    @Column(nullable = false)
    private double creditLimit;

    @Setter
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER , orphanRemoval = true)
    private List<Address> addresses;

    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRole role;


}