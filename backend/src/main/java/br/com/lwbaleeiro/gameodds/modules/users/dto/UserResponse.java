package br.com.lwbaleeiro.gameodds.modules.users.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email) {
}
