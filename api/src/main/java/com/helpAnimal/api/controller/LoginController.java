package com.helpAnimal.api.controller;

import com.helpAnimal.api.model.dto.AutenticacaoDTO;
import com.helpAnimal.api.model.dto.LoginDTO;
import com.helpAnimal.api.model.dto.RegistroDTO;
import com.helpAnimal.api.model.usuario.UsuarioModel;
import com.helpAnimal.api.repository.UsuarioRepository;
import com.helpAnimal.api.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/HelpAnimal/usuarios")
public class LoginController {

    @Autowired
     private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AutenticacaoDTO autenticacaoDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(), autenticacaoDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity register(@RequestBody @Validated RegistroDTO registroDTO){
        if(this.usuarioRepository.findByLogin(registroDTO.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registroDTO.getPassword());
        UsuarioModel novoUsuario = new UsuarioModel(registroDTO.getLogin(), encryptedPassword, registroDTO.getRole());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
