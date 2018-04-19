package ro.ubb.diary.service;

import ro.ubb.diary.model.Customer;
import ro.ubb.diary.model.Note;
import ro.ubb.diary.observer.Observer;

import java.util.List;

public interface NoteService {
	void insert(Note note);
	List<Note> loadAllNotes();
	//void register(Observer obs);
}
