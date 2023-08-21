package com.equipo2.Appkademy.core.security.model;

import com.equipo2.Appkademy.core.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long userId;

    @Column(name = "email", nullable = false)
    private String email;

    //Note that lombok is overriding getPassword from interface UserDetails, if not using lombok we need to override getPassword()
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "userTypeId", nullable = true)
    private Long UserTypeId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "_user_role",  // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"),  // Column in this entity's table
            inverseJoinColumns = @JoinColumn(name = "role_id")  // Column in the related entity's table
    )
    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Here goes the logic to search the permissions based on user roles
        Set<Permission> authorities  = new HashSet<>();
        roles.forEach(role -> {
            authorities.addAll(role.getPermissions());
        });
        return Collections.unmodifiableSet(authorities);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
