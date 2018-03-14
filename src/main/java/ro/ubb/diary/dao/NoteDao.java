package ro.ubb.diary.dao;

import ro.ubb.diary.model.Customer;
import ro.ubb.diary.model.Note;
import ro.ubb.diary.observer.Subject;

import java.util.List;

public interface NoteDao {
	void insert(Note note);
//	void inserBatch(List<Note> notes);
	List<Note> loadAllNotes();
//	String findTextById(long noteId);
//	Note findNoteById(long noteId);
}
