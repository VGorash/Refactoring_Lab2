package util;

import model.Contact;

import java.util.ArrayList;

public class ContactList extends ArrayList<Contact> {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Results (" + size() + ") :\n");
        for(int i=0; i<size(); i++){
            result.append("#").append(i+1).append("\n");
            result.append(get(i).toString()).append("\n");
        }
        return result.toString();
    }
}
