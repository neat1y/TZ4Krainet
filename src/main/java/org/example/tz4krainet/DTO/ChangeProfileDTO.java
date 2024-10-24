package org.example.tz4krainet.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProfileDTO {
    private  String password;
    private String lastname;
    private String firstname;
    private String username;
    private String OldPassword;
}
