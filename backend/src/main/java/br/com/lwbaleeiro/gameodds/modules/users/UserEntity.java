package br.com.lwbaleeiro.gameodds.modules.users;

import lombok.Data;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private Instant createdAt;

    public UserEntity() {
        this.createdAt = Instant.now();
    }

    public UserEntity(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
