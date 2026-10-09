package org.example.notesapi.service;

import jakarta.transaction.Transactional;
import org.example.notesapi.dto.CreateNoteRequest;
import org.example.notesapi.dto.NoteResponse;
import org.example.notesapi.dto.UpdateNoteRequest;
import org.example.notesapi.exception.NoteDoesNotExistException;
import org.example.notesapi.model.Note;
import org.example.notesapi.repository.NoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoteService {
    NoteRepository noteRepository;
    UserService userService;

    public NoteService(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    private NoteResponse toResponse(Note note) {
        Long userId = note.getUser() != null ? note.getUser().getId() : null;
        return new NoteResponse(note.getId(), note.getTitle(), note.getContent(), note.getCreatedAt(), userId);
    }

    public NoteResponse createNote(CreateNoteRequest request) {
        Note note = new Note();
        note.setTitle(request.title());
        note.setContent(request.content());
        note.setCreatedAt(new Date());
        note.setUser(userService.getCurrentUser());
        noteRepository.save(note);
        return toResponse(note);
    }

    public List<NoteResponse> getAllNotesByUserId() {
        List<Note> notes = noteRepository.findByUserId(userService.getCurrentUser().getId());
        return notes.stream().map(this::toResponse).toList();
    }

    private Note findNoteById(Long id) {
        return noteRepository.findByIdAndUserId(id, userService.getCurrentUser().getId()).orElseThrow(() -> new NoteDoesNotExistException(id));
    }

    public ResponseEntity<NoteResponse> getNoteById(Long id) {
        Note note = findNoteById(id);
        return ResponseEntity.ok(toResponse(note));
    }

    @Transactional
    public ResponseEntity<NoteResponse> updateNote(Long id, UpdateNoteRequest request) {
        Note note = findNoteById(id);
        note.setTitle(request.title());
        note.setContent(request.content());
        return  ResponseEntity.ok(toResponse(note));
    }
}
