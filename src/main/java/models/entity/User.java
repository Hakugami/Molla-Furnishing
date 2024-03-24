package models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    @Setter
    @Past(message = "Birthday must be in the past")
    @NotNull(message = "Birthday cannot be null")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Setter
    @NotBlank(message = "Password cannot be blank")
    @Column(nullable = false)
    private String password;

    @Setter
    @NotBlank(message = "Job cannot be blank")
    @Column(nullable = false)
    private String job;

    @Setter
    @NotBlank(message = "Interest cannot be blank")
    @Column(nullable = false)
    private String interest;

    @Setter
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @NotBlank(message = "Phone cannot be blank")
    @Column(nullable = false)
    private String phone;

    @Setter
    @NotBlank(message = "Gender cannot be blank")
    @Column(nullable = false)
    private String gender;

    @Setter
    @NotBlank(message = "Salt cannot be blank")
    @Column(nullable = false)
    private String salt;

    @Setter
    @Column(nullable = false)
    private double creditLimit;

    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Address> addresses;

    @Setter
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Role cannot be null")
    @Column(nullable = false)
    private UserRole role;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private ShoppingCart cart;

    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Order> orders;
}