package org.example.notesapi.dto;

public record UserResponse (
        Long id,
        String username,
        String email
) {
}
