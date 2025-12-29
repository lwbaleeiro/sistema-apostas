package br.com.lwbaleeiro.gameodds.modules.users.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lwbaleeiro.gameodds.modules.users.UserEntity;
import br.com.lwbaleeiro.gameodds.modules.users.UserRepository;
import br.com.lwbaleeiro.gameodds.modules.users.dto.UserRequest;
import br.com.lwbaleeiro.gameodds.modules.users.dto.UserResponse;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Não usamos mais Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse create(UserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Var não é tipagem dinamica, o lado direito deve ser de um tipo explicito.
        var entity = new UserEntity(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()));

        var savedUser = userRepository.save(entity);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail());
    }
}
