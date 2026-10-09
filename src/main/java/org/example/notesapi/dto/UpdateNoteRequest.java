package org.example.notesapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNoteRequest (
        @NotBlank(message = "title is required")
        String title,
        @NotBlank(message = "content is required")
        @Size(max = 5000, message = "content cannot exceed 5000 characters")
        String content
) {
}
