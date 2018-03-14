package ro.ubb.diary.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.diary.dao.NoteDao;
import ro.ubb.diary.model.Note;
import ro.ubb.diary.observer.Observer;
import ro.ubb.diary.observer.Subject;
import ro.ubb.diary.service.NoteService;

import java.util.List;


@Service
public class NoteServiceImpl extends Subject implements NoteService{


	Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

	@Autowired
	NoteDao noteDao;
	
	@Override
	public void insert(Note note) {
		logger.info("Calling insert on noteDao");
		noteDao.insert(note);
		super.notifyAllObservers();
	}

	public List<Note> loadAllNotes(){
		logger.info("Calling loadAllNotes on noteDao");
		List<Note> listCust = noteDao.loadAllNotes();
		return listCust;
	}


	public void register(Observer obs) {
		logger.info("Register " + obs);
		super.register(obs);
	}


}
