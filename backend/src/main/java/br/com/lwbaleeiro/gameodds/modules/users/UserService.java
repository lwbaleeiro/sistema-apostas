package br.com.lwbaleeiro.gameodds.modules.users;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Não usamos mais Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse create(UserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Var não é tipagem dinamica, o lado direito deve ser de um tipo explicito.
        var entity = new UserEntity(request.name(), request.email(), request.password());

        var savedUser = userRepository.save(entity);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail());
    }
}
