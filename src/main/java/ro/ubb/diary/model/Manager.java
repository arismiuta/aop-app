package ro.ubb.diary.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.diary.service.NoteService;
import ro.ubb.diary.service.impl.NoteServiceImpl;

public class Manager
{
    NoteService noteService;

    int id;
    String name;

    public Manager(NoteService noteService) {
        this.noteService = noteService;
        this.id = 10;
        this.name = "Dorin";
    }


}
