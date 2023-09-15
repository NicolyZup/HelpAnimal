package com.helpAnimal.api.controller;

import com.helpAnimal.api.exception.CustomException;
import com.helpAnimal.api.model.AjudaModel;
import com.helpAnimal.api.model.dto.AjudaDTO;
import com.helpAnimal.api.service.AjudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/HelpAnimal/ajudas")
public class AjudaController {

    @Autowired
    AjudaService ajudaService;

    @GetMapping
    public ResponseEntity<?> listarAjudas(){
        if(ajudaService.listar().size() == 0){
            return ResponseEntity.ok("Nenhuma ajuda cadastrada ainda.");
        } else{
            return ResponseEntity.ok(ajudaService.listar());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<AjudaDTO>> listarAjudaPorId(@PathVariable Long id){
        return ResponseEntity.ok(ajudaService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AjudaDTO> cadastrarAjuda(@RequestBody AjudaDTO ajudaDTO){
        AjudaDTO ajudaCriada = ajudaService.criar(ajudaDTO);

        return new ResponseEntity<>(ajudaCriada
                , HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<AjudaDTO> atenderAjuda(@PathVariable Long id){
        return ResponseEntity.ok(ajudaService.atendida(id));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> excluirAjuda(@PathVariable Long id){
        ajudaService.excluir(id);
        return ResponseEntity.ok("Ajuda excluída!");
    }
}
