package br.com.lwbaleeiro.gameodds.modules.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
}
