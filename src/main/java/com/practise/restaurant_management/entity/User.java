package com.practise.restaurant_management.entity;

import com.practise.restaurant_management.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(unique = true, name = "email",nullable = false)
    private String email;

    @Column(name = "phone_number",unique = true)
    private String phoneNumber;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Staff staff;

    @OneToMany(mappedBy = "managerUser")
    private List<Staff> managerStaff;

    @OneToOne(mappedBy = "manager")
    private Branches branches;

    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurantList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role));
    }


    @Override
    public String getUsername() {
        return email;
    }
}
