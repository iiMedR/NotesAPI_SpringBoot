package org.example.notesapi.controller;

import jakarta.validation.Valid;
import org.example.notesapi.dto.CreateNoteRequest;
import org.example.notesapi.dto.NoteResponse;
import org.example.notesapi.dto.UpdateNoteRequest;
import org.example.notesapi.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public NoteResponse createNote(@Valid @RequestBody CreateNoteRequest request) {
        return noteService.createNote(request);
    }

    @GetMapping
    public List<NoteResponse> getAllNotes() {
        return noteService.getAllNotesByUserId();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable Long id, @Valid @RequestBody UpdateNoteRequest request) {
        return noteService.updateNote(id, request);
    }

}
