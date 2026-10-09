package org.example.notesapi.exception;

public class NoteDoesNotExistException extends RuntimeException {
    public NoteDoesNotExistException(Long id) {
        super(
                "Note with id: " + id + " does not exist"
        );
    }
}
