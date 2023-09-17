package com.helpAnimal.api.service;

import com.helpAnimal.api.exception.CustomException;
import com.helpAnimal.api.model.publicacao.PublicacaoModel;
import com.helpAnimal.api.model.dto.AjudaDTO;
import com.helpAnimal.api.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacaoService {
    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public List<AjudaDTO> listar(){
        List<PublicacaoModel> ajudas = publicacaoRepository.findAll();
        List<AjudaDTO> ajudasDTOs = new ArrayList<>();

        for(PublicacaoModel ajuda : ajudas){
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

        Optional<PublicacaoModel> ajudaModel = publicacaoRepository.findById(id);

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
        PublicacaoModel ajudaModel = new PublicacaoModel();

        ajudaModel.setAnimal(ajudaDTO.getAnimal());
        ajudaModel.setDescricao(ajudaDTO.getDescricao());
        ajudaModel.setLocalidade(ajudaDTO.getLocalidade());
        ajudaModel.setAjudaAtendida(ajudaDTO.getAjudaAtendida());

        publicacaoRepository.save(ajudaModel);
        return ajudaDTO;
    }

    public AjudaDTO atendida(Long id){
        Optional<PublicacaoModel> ajudaModel = publicacaoRepository.findById(id);

        if(ajudaModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }

        if(ajudaModel.get() != null){
            ajudaModel.get().setAjudaAtendida(!ajudaModel.get().getAjudaAtendida());
        }

        publicacaoRepository.save(ajudaModel.get());
        AjudaDTO ajudaDTO = new AjudaDTO();

        ajudaDTO.setAnimal(ajudaModel.get().getAnimal());
        ajudaDTO.setDescricao(ajudaModel.get().getDescricao());
        ajudaDTO.setLocalidade(ajudaModel.get().getLocalidade());
        ajudaDTO.setAjudaAtendida(ajudaModel.get().getAjudaAtendida());

        return ajudaDTO;
    }

    public void excluir(Long id){
        Optional<PublicacaoModel> ajudaModel = publicacaoRepository.findById(id);

        if(ajudaModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }else{
            publicacaoRepository.deleteById(id);
        }
    }

}
