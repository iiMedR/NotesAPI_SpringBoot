package org.example.notesapi.repository;

import org.example.notesapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long id);
    Optional<Note> findByIdAndUserId(Long id, Long userId);
}
