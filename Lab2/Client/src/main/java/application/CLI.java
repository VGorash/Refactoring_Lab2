package application;

import model.Contact;
import util.ContactList;
import util.Requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CLI {

    private final BufferedReader reader;
    private String defaultQuery;
    private String username;

    public CLI(){
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run(){
        username = read("Enter your username:");
        defaultQuery = "?user_id=" + username;
        boolean exitFlag = false;
        while (!exitFlag){
            exitFlag = makeDialog();
        }
    }

    private String read(String prompt){
        System.out.print(prompt+" ");
        try {
            return reader.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private boolean makeDialog(){
        System.out.println("""
                Menu:
                1. View all contacts
                2. Search
                3. New contact
                4. Exit""");
        switch (Objects.requireNonNull(read(">"))) {
            case "1" -> {
                getAll();
                return false;
            }
            case "2" -> {
                search();
                return false;
            }
            case "3" -> {
                add();
                return false;
            }
            case "4" -> {
                return true;
            }
            default -> {
                System.out.println("Unknown command");
                return false;
            }
        }
    }

    private void getAll(){
        System.out.println("Loading results...");
        try {
            ContactList list = Requests.getContacts(defaultQuery);
            System.out.println(list);
        }
        catch (IOException | InterruptedException  e){
            System.out.println("Problems with server");
        }
    }

    private void search(){
        String query;
        System.out.println("""
                Search by
                1. Name
                2. Surname
                3. Name and Surname
                4. Phone
                5. E-mail
                6. All fields""");
        switch (Objects.requireNonNull(read(">"))) {
            case "1" -> query = defaultQuery + "&search_type=NAME";
            case "2" -> query = defaultQuery + "&search_type=SURNAME";
            case "3" -> query = defaultQuery + "&search_type=FULLNAME";
            case "4" -> query = defaultQuery + "&search_type=PHONE";
            case "5" -> query = defaultQuery + "&search_type=EMAIL";
            case "6" -> query = defaultQuery + "&search_type=ALL";
            default -> {
                System.out.println("Unknown command");
                return;
            }
        }
        String request = read("Request:");
        if(request == null){
            return;
        }
        query += "&query="+request.replace("+", "%2B");
        System.out.println("Loading results...");
        try {
            ContactList list = Requests.getContacts(query);
            System.out.println(list);
        }
        catch (IOException | InterruptedException  e){
            System.out.println("Problems with server");
        }
    }

    private void add(){
        System.out.println("New contact");
        Contact contact = new Contact();
        contact.setName(read("Name:"));
        contact.setSurname(read("Surname:"));
        contact.setPhone(read("Phone number:"));
        contact.setEmail(read("E-Mail:"));
        contact.setUserId(username);
        System.out.println("Processing request...");
        try {
            Contact addedContact = Requests.addContact(contact);
            if(addedContact != null){
                System.out.println("Added successfully");
            }
            else{
                System.out.println("Error during adding");
            }
        }
        catch (IOException | InterruptedException  e){
            System.out.println("Problems with server");
        }
    }
}
