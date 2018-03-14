package ro.ubb.diary.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.diary.Main;
import ro.ubb.diary.model.Customer;
import ro.ubb.diary.model.Note;
import ro.ubb.diary.service.CustomerService;
import ro.ubb.diary.service.NoteService;

import java.sql.Time;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Console {


    Logger logger = LoggerFactory.getLogger(Console.class);


    private CustomerService customerService;
    private NoteService noteService;
    private Scanner scanner;

    public Console(CustomerService customerService, NoteService noteService) {
        this.customerService = customerService;
        this.noteService = noteService;
        scanner = new Scanner(System.in);
    }

    public void run() {

        menu();

        while(true){

            String result = command();

            if(result.equals("exit")){
                logger.info("User exit");
                break;
            }

        }
    }

    private String command() {
        System.out.print(">>> ");
        String input = scanner.nextLine();

        if(input.startsWith("add")) {
            _handleAdd(input);
        }
        else if(input.startsWith("list")) {
            _handleList(input);
        }
        else {
            logger.info("User input invalid.");
            System.out.println("Invalid command!");
        }

        return input;
    }

    private void _handleAdd(String input) {

        String[] tokens = input.split(" ");

        if(tokens.length > 1)
            if(tokens[1].equals("customer")){
                _customerAdd(tokens);
            }
            else if (tokens[1].equals("note")){
                _noteAdd(tokens);
            }
            else {
                System.out.println("Invalid command #2");
            }
        else
            System.out.println("Invalid command #3");

    }

    private void _noteAdd(String[] tokens) {
        if(tokens.length == 5){
            long note_id = Long.valueOf(tokens[2]);
            long cust_id = Long.valueOf(tokens[3]);
            String text = tokens[4];

            long now = Calendar.getInstance().getTimeInMillis();

            Note note = new Note(note_id, cust_id, new Date(now), new Time(now), text);

            noteService.insert(note);
        }
    }

    private void _customerAdd(String[] tokens) {
        if(tokens.length == 5){
            long id = Long.valueOf(tokens[2]);
            String name = tokens[3];
            int age = Integer.valueOf(tokens[4]);

            Customer customer = new Customer(
                    id, name, age
            );

            customerService.insert(customer);
        }
    }

    private void _handleList(String input) {
        String[] tokens = input.split(" ");
        if(tokens.length == 2)
            if(tokens[1].equals("customer")){
                List<Customer> customerList = customerService.loadAllCustomer();
                System.out.println("==== Customers ====");
                customerList.forEach(System.out::println);
                System.out.println("===================");
            }
            else if(tokens[1].equals("note")){
                List<Note> noteList = noteService.loadAllNotes();
                System.out.println("==== Notes ====");
                noteList.forEach(System.out::println);
                System.out.println("===================");
            }
    }

    private void menu() {
        System.out.println("Available commands");
        System.out.println("\t add \t customer [id] [name] [age]");
        System.out.println("\t\t\t note [id] [customer-id] [text]");

        System.out.println("");

        System.out.println("\t list \t customer");
        System.out.println("\t\t\t note");
        System.out.println("");
    }
}
