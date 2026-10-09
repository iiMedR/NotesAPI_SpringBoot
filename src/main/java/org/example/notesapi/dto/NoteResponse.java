package org.example.notesapi.dto;

import java.util.Date;

public record NoteResponse (
        Long id,
        String title,
        String content,
        Date createdAt,
        Long userId
) {
}
