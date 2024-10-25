package org.example.tz4krainet.Service;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.ChangeProfileDTO;
import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Exception.PasswordIncorrectException;
import org.example.tz4krainet.Exception.UserNotFoundException;
import org.example.tz4krainet.Exception.UserWasFoundException;
import org.example.tz4krainet.Mappers.UserMapper;
import org.example.tz4krainet.Models.Users;
import org.example.tz4krainet.Repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UserMapper usersMappers;
    private final PasswordEncoder encoder;
    // сохраняет пользователя, если не получится выкинет ошибку что пользователь не найден и не сохранен
    public Users save(UsersDTO dto){
        Users users=usersMappers.userDTOToUser(dto);
        users.setUserRole("ROLE_USER");
        usersRepository.save(users);
        return usersRepository.findByUsername(dto.getUsername()).orElseThrow(()->new UserNotFoundException("dont save"));
    }
    // Поиск по id
    public Optional<Users> findById(Integer userId) {
       return usersRepository.findById(userId);
    }
    // Поиск по имени (username)
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }
    // Изменить профиль, вводя пароль, если он правильный то идет дальше изменение
    // Если нового имени нет в бд он его обновит, если есть выдаст ошибку.
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
            else{
                throw new UserWasFoundException("this username has different user");
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
    //Удаление пользователя
    public void delete(Integer id, String password) {
        Users users=usersRepository.findById(id).get();
        if(!encoder.matches(password,users.getPassword())){
            throw new PasswordIncorrectException("Password Incorrect");
        }
        usersRepository.delete(users);
    }

    public List<Users> findAll() {
       return usersRepository.findAll();
    }
}
