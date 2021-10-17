package model;

import lombok.*;

import java.io.Serializable;

@Data
public class Contact implements Serializable {

    private Integer id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    private String userId;

    @Override
    public String toString() {
        String result = "";
        if(name != null) result += "Name: " + name + "\n";
        if(surname != null) result += "Surname: " + surname + "\n";
        if(phone != null) result += "Phone number: " + phone + "\n";
        if(email != null) result += "E-Mail: " + email + "\n";
        return result;
    }
}