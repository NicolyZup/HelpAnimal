package com.helpAnimal.api.service;

import com.helpAnimal.api.exception.CustomException;
import com.helpAnimal.api.model.AjudaModel;
import com.helpAnimal.api.model.dto.AjudaDTO;
import com.helpAnimal.api.repository.AjudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AjudaService {
    @Autowired
    private AjudaRepository ajudaRepository;

    public List<AjudaDTO> listar(){
        List<AjudaModel> ajudas = ajudaRepository.findAll();
        List<AjudaDTO> ajudasDTOs = new ArrayList<>();

        for(AjudaModel ajuda : ajudas){
            AjudaDTO ajudaDTO = new AjudaDTO();

            ajudaDTO.setAnimal(ajuda.getAnimal());
            ajudaDTO.setDescricao(ajuda.getDescricao());
            ajudaDTO.setLocalidade(ajuda.getLocalidade());
            ajudaDTO.setAjudaAtendida(ajuda.getAjudaAtendida());

            ajudasDTOs.add(ajudaDTO);
        }

        return ajudasDTOs;
    }

    public Optional<AjudaDTO> listarPorId(Long id){

        Optional<AjudaModel> ajudaModel = ajudaRepository.findById(id);

        if(ajudaModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }

        AjudaDTO ajudaDTO = new AjudaDTO();
        ajudaDTO.setAnimal(ajudaModel.get().getAnimal());
        ajudaDTO.setDescricao(ajudaModel.get().getDescricao());
        ajudaDTO.setLocalidade(ajudaModel.get().getLocalidade());
        ajudaDTO.setAjudaAtendida(ajudaModel.get().getAjudaAtendida());
        return Optional.of(ajudaDTO);
    }

    public AjudaDTO criar(AjudaDTO ajudaDTO){
        AjudaModel ajudaModel = new AjudaModel();

        ajudaModel.setAnimal(ajudaDTO.getAnimal());
        ajudaModel.setDescricao(ajudaDTO.getDescricao());
        ajudaModel.setLocalidade(ajudaDTO.getLocalidade());
        ajudaModel.setAjudaAtendida(ajudaDTO.getAjudaAtendida());

        ajudaRepository.save(ajudaModel);
        return ajudaDTO;
    }

    public AjudaDTO atendida(Long id){
        Optional<AjudaModel> ajudaModel = ajudaRepository.findById(id);

        if(ajudaModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }

        if(ajudaModel.get() != null){
            ajudaModel.get().setAjudaAtendida(!ajudaModel.get().getAjudaAtendida());
        }

        ajudaRepository.save(ajudaModel.get());
        AjudaDTO ajudaDTO = new AjudaDTO();

        ajudaDTO.setAnimal(ajudaModel.get().getAnimal());
        ajudaDTO.setDescricao(ajudaModel.get().getDescricao());
        ajudaDTO.setLocalidade(ajudaModel.get().getLocalidade());
        ajudaDTO.setAjudaAtendida(ajudaModel.get().getAjudaAtendida());

        return ajudaDTO;
    }

    public void excluir(Long id){
        Optional<AjudaModel> ajudaModel = ajudaRepository.findById(id);

        if(ajudaModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }else{
            ajudaRepository.deleteById(id);
        }
    }

}
