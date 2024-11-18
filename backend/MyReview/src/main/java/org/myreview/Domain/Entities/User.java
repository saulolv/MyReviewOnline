package org.myreview.Domain.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.myreview.Domain.Enums.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    private Boolean lockedProfile = false;
    private Date createdAt = new Date();

    @OneToMany(mappedBy = "user")
    private ArrayList<Review> reviews = new ArrayList<>();
}
