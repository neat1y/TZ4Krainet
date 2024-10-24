package org.example.tz4krainet.Service;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ChangeProfileDTO;
import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Exception.PasswordIncorrectException;
import org.example.tz4krainet.Exception.UserNotFoundException;
import org.example.tz4krainet.Mappers.UserMapper;
import org.example.tz4krainet.Models.Users;
import org.example.tz4krainet.Repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UserMapper usersMappers;
    private final PasswordEncoder encoder;
    public Users save(UsersDTO dto){
        Users users=usersMappers.userDTOToUser(dto);
        users.setUserRole("ROLE_USER");
        usersRepository.save(users);
        return usersRepository.findByUsername(dto.getUsername()).orElseThrow(()->new UserNotFoundException("dont save"));
    }

    public Optional<Users> findById(Integer userId) {
       return usersRepository.findById(userId);
    }

    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public void change(ChangeProfileDTO u, Integer id) {
        Users users1=usersRepository.findById(id).get();
        if(!encoder.matches(u.getOldPassword(),users1.getPassword())){
            throw new PasswordIncorrectException("Password Incorrect");
        }
        if(u.getPassword() != null) {
            users1.setPassword(encoder.encode(u.getPassword()));
        }
        if(u.getUsername() != null){
            Optional<Users> users= usersRepository.findByUsername(u.getUsername());
            if(users.isEmpty()){
                users1.setUsername(u.getUsername());
            }
        }
        if(u.getFirstname()!= null){
            users1.setFirstname(u.getFirstname());
        }
        if(u.getLastname()!= null){
            users1.setLastname(u.getLastname());
        }
        usersRepository.save(users1);
    }

    public void delete(Integer id, String password) {
        Users users=usersRepository.findById(id).get();
        if(!encoder.matches(password,users.getPassword())){
            throw new PasswordIncorrectException("Password Incorrect");
        }
        usersRepository.delete(users);
    }
}
