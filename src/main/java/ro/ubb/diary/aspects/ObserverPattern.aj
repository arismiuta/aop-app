package ro.ubb.diary.aspects;

import ro.ubb.diary.service.NoteService;
import ro.ubb.diary.service.impl.NoteServiceImpl;
import ro.ubb.diary.model.Manager;

import java.util.ArrayList;
import java.util.List;

public aspect ObserverPattern {

    declare parents: NoteService implements Subject;
    declare parents: Manager implements Observer;

    private List<Observer> Subject.observers = new ArrayList<>();

    public void Subject.addObserver(Observer obs){
        System.out.println("Adding observer");
        observers.add(obs);
    }
    public void Subject.removeObserver(Observer obs){
        System.out.println("Removing observer");
        observers.remove(obs);

    }
    public void Subject.notifyObservers(Object o){
        for(Observer ob : observers)
            ob.update(o);
    }

    pointcut observed(NoteServiceImpl noteService):execution(
        * ro.ubb.diary.service.impl.NoteServiceImpl.insert*(..)
        ) && this(noteService);

    NoteService ns;

    after(NoteService noteService, Manager manager): initialization(Manager.new(*))
            && this(manager) && args(noteService){
        System.out.println("dsadasdsa");
        noteService.addObserver(manager);
        ns=noteService;
    }

    //observers notification
    after(NoteServiceImpl ns) returning: observed(ns){
        System.out.println("Inside ObserverAspect: notifyObservers");
        ns.notifyObservers(null);
    }

    //observer action
    public void Manager.update(Object o){
        System.out.println("Inside ObserverAspect: update ");
    }

}
