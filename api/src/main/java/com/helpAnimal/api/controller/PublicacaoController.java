package com.helpAnimal.api.controller;

import com.helpAnimal.api.model.dto.ComentarioRequestDTO;
import com.helpAnimal.api.model.dto.ComentarioResponseDTO;
import com.helpAnimal.api.model.dto.PublicacaoDTO;
import com.helpAnimal.api.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/HelpAnimal/publicacoes")
public class PublicacaoController {

    @Autowired
    PublicacaoService publicacaoService;

    @GetMapping
    public ResponseEntity<?> listarPublicacoes(){
        if(publicacaoService.listar().size() == 0){
            return ResponseEntity.ok("Nenhuma publicação de ajuda cadastrada ainda.");
        } else{
            return ResponseEntity.ok(publicacaoService.listar());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<PublicacaoDTO>> listarPublicacaoPorId(@PathVariable Long id){
        return ResponseEntity.ok(publicacaoService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacaoDTO> postarPublicacao(@RequestBody PublicacaoDTO publicacaoDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        PublicacaoDTO publicacaoCriada = publicacaoService.criar(publicacaoDTO, nomeUsuario);

        return new ResponseEntity<>(publicacaoCriada
                , HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PublicacaoDTO> atenderAjuda(@PathVariable Long id){
        return ResponseEntity.ok(publicacaoService.atendida(id));
    }

    @PutMapping(path = "/{id}/comentar")
    public ResponseEntity<ComentarioResponseDTO> comentarPublicacao(@PathVariable Long id, @RequestBody ComentarioRequestDTO comentarioRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        return ResponseEntity.ok(publicacaoService.comentar(id,comentarioRequestDTO, nomeUsuario));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> excluirPublicacao(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        for(GrantedAuthority authority:authentication.getAuthorities()){
            String role = authority.getAuthority();
            publicacaoService.excluir(id, nomeUsuario, role);
            System.out.println(role);
        }
        return ResponseEntity.ok("Publicação excluída!");
    }
}
