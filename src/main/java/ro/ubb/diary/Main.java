package ro.ubb.diary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ro.ubb.diary.console.Console;
import ro.ubb.diary.model.Manager;
import ro.ubb.diary.service.CustomerService;
import ro.ubb.diary.service.NoteService;
import ro.ubb.diary.utils.Present;

@SpringBootApplication
@ComponentScan("ro.ubb.diary.service.impl, ro.ubb.diary.dao.impl")
public class Main {

    @Autowired
	CustomerService cusService;

    @Autowired
    NoteService noteService;

	public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        ApplicationContext context = SpringApplication.run(Main.class, args);
        CustomerService cusService = context.getBean(CustomerService.class);
        NoteService noteService = context.getBean(NoteService.class);

        Manager manager = new Manager(2, "Admin");

        noteService.register(manager);

        Present.initConsole();

        Console console = new Console(cusService, noteService);
        console.run();

	}
}
