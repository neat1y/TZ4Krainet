package org.example.tz4krainet.Mappers;

import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Models.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(target = "password", source = "password")
//    @Mapping(target = "lastname", source = "lastname")
//    @Mapping(target = "firstname", source = "firstname")
    UsersDTO userToUserDTO(Users user);
    @Mapping(target = "password", source = "password")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "username", source = "username")
    Users userDTOToUser(UsersDTO userDTO);
}

