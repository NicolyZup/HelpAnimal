package com.helpAnimal.api.controller;

import com.helpAnimal.api.model.dto.AjudaDTO;
import com.helpAnimal.api.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/HelpAnimal/ajudas")
public class PublicacaoController {

    @Autowired
    PublicacaoService publicacaoService;

    @PreAuthorize("hasAnyRole('user', 'admin')")
    @GetMapping
    public ResponseEntity<?> listarAjudas(){
        if(publicacaoService.listar().size() == 0){
            return ResponseEntity.ok("Nenhuma ajuda cadastrada ainda.");
        } else{
            return ResponseEntity.ok(publicacaoService.listar());
        }
    }

    @PreAuthorize("hasAnyRole('user', 'admin')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<AjudaDTO>> listarAjudaPorId(@PathVariable Long id){
        return ResponseEntity.ok(publicacaoService.listarPorId(id));
    }

    @PreAuthorize("hasAnyRole('user', 'admin')")
    @PostMapping
    public ResponseEntity<AjudaDTO> cadastrarAjuda(@RequestBody AjudaDTO ajudaDTO){
        AjudaDTO ajudaCriada = publicacaoService.criar(ajudaDTO);

        return new ResponseEntity<>(ajudaCriada
                , HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('user', 'admin')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AjudaDTO> atenderAjuda(@PathVariable Long id){
        return ResponseEntity.ok(publicacaoService.atendida(id));
    }


    @DeleteMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> excluirAjuda(@PathVariable Long id){
        publicacaoService.excluir(id);
        return ResponseEntity.ok("Ajuda excluída!");
    }
}
