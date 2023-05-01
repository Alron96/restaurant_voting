package com.restaurant_voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AbstractNamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @Column(name = "role")
    @JoinColumn
    @ElementCollection(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    public User(Integer id, String name, String email, String password, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        setRoles(roles);
    }

    public User(Integer id, String name, String email, String password, Role... roles) {
        this(id, name, email, password, List.of(roles));
    }

    public User(User u) {
        this(u.id, u.name, u.email, u.password, u.roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        String id_name = super.toString();
        return "User(" +
                id_name +
                ", email=" + this.getEmail() +
                ", roles=" + this.getRoles() +
                ")";
    }
}
