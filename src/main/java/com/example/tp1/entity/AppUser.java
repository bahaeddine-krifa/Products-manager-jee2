package com.example.tp1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    private String id;
    @Column(unique = true)
    private String userName;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}
