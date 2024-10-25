package org.example.tz4krainet.Controllers;

import lombok.AllArgsConstructor;
import org.example.tz4krainet.DTO.LoginDTO;
import org.example.tz4krainet.DTO.UsersDTO;
import org.example.tz4krainet.Models.Users;
import org.example.tz4krainet.Security.JWTUtils;
import org.example.tz4krainet.Service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final JWTUtils jwtUtils;
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;
    //Регистрация пользователя
    @PostMapping("/reg")
    public ResponseEntity<?> create_users(@RequestBody UsersDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Users users= usersService.save(dto);
        return ResponseEntity.ok(jwtUtils.generateToken(users));
    }
    // Аутентефикация пользователя
    @PostMapping("/login")
    public ResponseEntity<?> auth_users(@RequestBody LoginDTO dto){
       Users users= usersService.findByUsername(dto.getUsername());
       Boolean flag= passwordEncoder.matches(dto.getPassword(), users.getPassword());
       if(flag==Boolean.TRUE){
           return ResponseEntity.ok(jwtUtils.generateToken(users));
       }
       return ResponseEntity.ok("Invalid password");
    }
}
