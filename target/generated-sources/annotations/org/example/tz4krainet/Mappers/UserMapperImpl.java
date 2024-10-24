package org.example.tz4krainet.Mappers;

import javax.annotation.processing.Generated;
import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Models.Users;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-24T06:22:33+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Arch Linux)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UsersDTO userToUserDTO(Users user) {
        if ( user == null ) {
            return null;
        }

        UsersDTO usersDTO = new UsersDTO();

        usersDTO.setPassword( user.getPassword() );
        usersDTO.setLastname( user.getLastname() );
        usersDTO.setFirstname( user.getFirstname() );
        usersDTO.setUsername( user.getUsername() );

        return usersDTO;
    }

    @Override
    public Users userDTOToUser(UsersDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Users users = new Users();

        users.setPassword( userDTO.getPassword() );
        users.setLastname( userDTO.getLastname() );
        users.setFirstname( userDTO.getFirstname() );
        users.setUsername( userDTO.getUsername() );

        return users;
    }
}
