package petermartesc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import petermartesc.springboot.dto.user.UserDtoAuth;
import petermartesc.springboot.security.AuthService;
import petermartesc.springboot.service.rest.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthRESTController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService usuarioService;

    @PostMapping("/register/")
    public String register(@RequestBody UserDtoAuth userDto ) {
        //return "recibe: "+u.nombre + " "+ u.password;
        String token = authService.register(userDto.name(), userDto.password());
        //return ResponseEntity.ok(token);
        return token;
    }

    @PostMapping("/login/")
    public String login(@RequestBody UserDtoAuth userDto ) {
        //return "recibe: "+u.nombre + " "+ u.password;
        String token = authService.authenticate(userDto.name(), userDto.password());
        //System.out.println(token);
        if ( token == null ) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return token;
    }
}
