package org.example.tz4krainet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UsersDTO {
    private  String password;
    private String lastname;
    private String firstname;
    private String username;

    public String getPassword() {
        return password;
    }

    public UsersDTO() {
    }

    public UsersDTO(String password, String lastname, String firstname, String username) {
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
