package br.com.lwbaleeiro.gameodds.modules.users.dto;

public record AuthRequest(
        String email,
        String password) {
}
