package ro.ubb.diary.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.diary.observer.Observer;

public class Manager extends Observer {

    Logger logger = LoggerFactory.getLogger(Manager.class);

    int id;
    String name;

    public Manager() {}

    public Manager(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void update() {
        logger.info("Observer " + name + " received update.");
    }

}
