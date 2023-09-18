package com.helpAnimal.api.service;

import com.helpAnimal.api.exception.CustomException;
import com.helpAnimal.api.model.dto.ComentarioRequestDTO;
import com.helpAnimal.api.model.dto.ComentarioResponseDTO;
import com.helpAnimal.api.model.publicacao.ComentarioModel;
import com.helpAnimal.api.model.publicacao.PublicacaoModel;
import com.helpAnimal.api.model.dto.PublicacaoDTO;
import com.helpAnimal.api.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacaoService {
    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public List<PublicacaoDTO> listar(){
        List<PublicacaoModel> publicacoes = publicacaoRepository.findAll();
        List<PublicacaoDTO> publicacoesDTOs = new ArrayList<>();

        for(PublicacaoModel publicacao : publicacoes){
            PublicacaoDTO publicacaoDTO = new PublicacaoDTO();

            publicacaoDTO.setAnimal(publicacao.getAnimal());
            publicacaoDTO.setDescricao(publicacao.getDescricao());
            publicacaoDTO.setLocalidade(publicacao.getLocalidade());
            publicacaoDTO.setAjudaAtendida(publicacao.getAjudaAtendida());
            publicacaoDTO.setUsuario(publicacao.getUsuario());

            List<ComentarioResponseDTO> comentarioResponseDTOS = new ArrayList<>();
            for(ComentarioModel comentarioModel:publicacao.getComentarios()){
                ComentarioResponseDTO comentarioResponseDTO = new ComentarioResponseDTO();
                comentarioResponseDTO.setUsuario(comentarioModel.getUsuario());
                comentarioResponseDTO.setData(comentarioModel.getData());
                comentarioResponseDTO.setTexto(comentarioModel.getTexto());
                comentarioResponseDTOS.add(comentarioResponseDTO);
            }

            publicacaoDTO.setComentarios(comentarioResponseDTOS);

            publicacoesDTOs.add(publicacaoDTO);
        }

        return publicacoesDTOs;
    }

    public Optional<PublicacaoDTO> listarPorId(Long id){

        Optional<PublicacaoModel> publicacaoModel = publicacaoRepository.findById(id);

        if(publicacaoModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }

        PublicacaoDTO publicacaoDTO = new PublicacaoDTO();
        publicacaoDTO.setAnimal(publicacaoModel.get().getAnimal());
        publicacaoDTO.setDescricao(publicacaoModel.get().getDescricao());
        publicacaoDTO.setLocalidade(publicacaoModel.get().getLocalidade());
        publicacaoDTO.setAjudaAtendida(publicacaoModel.get().getAjudaAtendida());
        publicacaoDTO.setUsuario(publicacaoModel.get().getUsuario());

        List<ComentarioResponseDTO> comentarioResponseDTOS = new ArrayList<>();
        for(ComentarioModel comentarioModel:publicacaoModel.get().getComentarios()){
            ComentarioResponseDTO comentarioResponseDTO = new ComentarioResponseDTO();
            comentarioResponseDTO.setUsuario(comentarioModel.getUsuario());
            comentarioResponseDTO.setData(comentarioModel.getData());
            comentarioResponseDTO.setTexto(comentarioModel.getTexto());
            comentarioResponseDTOS.add(comentarioResponseDTO);
        }

        publicacaoDTO.setComentarios(comentarioResponseDTOS);

        return Optional.of(publicacaoDTO);
    }

    public PublicacaoDTO criar(PublicacaoDTO publicacaoDTO, String nomeUsuario){
        PublicacaoModel publicacaoModel = new PublicacaoModel();

        publicacaoModel.setAnimal(publicacaoDTO.getAnimal());
        publicacaoModel.setDescricao(publicacaoDTO.getDescricao());
        publicacaoModel.setLocalidade(publicacaoDTO.getLocalidade());
        publicacaoModel.setAjudaAtendida(publicacaoDTO.getAjudaAtendida());
        publicacaoModel.setUsuario(nomeUsuario);
        publicacaoDTO.setUsuario(nomeUsuario);

        publicacaoRepository.save(publicacaoModel);
        return publicacaoDTO;
    }

    public PublicacaoDTO atendida(Long id){
        Optional<PublicacaoModel> publicacaoModel = publicacaoRepository.findById(id);

        if(publicacaoModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }

        if(publicacaoModel.get() != null){
            publicacaoModel.get().setAjudaAtendida(!publicacaoModel.get().getAjudaAtendida());
        }

        publicacaoRepository.save(publicacaoModel.get());
        PublicacaoDTO publicacaoDTO = new PublicacaoDTO();

        publicacaoDTO.setAnimal(publicacaoModel.get().getAnimal());
        publicacaoDTO.setDescricao(publicacaoModel.get().getDescricao());
        publicacaoDTO.setLocalidade(publicacaoModel.get().getLocalidade());
        publicacaoDTO.setAjudaAtendida(publicacaoModel.get().getAjudaAtendida());
        publicacaoDTO.setUsuario(publicacaoModel.get().getUsuario());

        return publicacaoDTO;
    }

    public ComentarioResponseDTO comentar(Long id, ComentarioRequestDTO comentarioRequestDTO, String nomeUsuario){
        Optional<PublicacaoModel> publicacaoModel = publicacaoRepository.findById(id);

        if(publicacaoModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }

        ComentarioModel comentarioModel = new ComentarioModel();
        comentarioModel.setData(LocalDate.now());
        comentarioModel.setUsuario(nomeUsuario);
        comentarioModel.setTexto(comentarioRequestDTO.getTexto());

        publicacaoModel.get().getComentarios().add(comentarioModel);
        publicacaoRepository.save(publicacaoModel.get());

        ComentarioResponseDTO comentarioDTO1 = new ComentarioResponseDTO();
        comentarioDTO1.setTexto(comentarioRequestDTO.getTexto());
        comentarioDTO1.setData(comentarioModel.getData());
        comentarioDTO1.setUsuario(comentarioModel.getUsuario());

        return comentarioDTO1;
    }


    public void excluir(Long id, String nomeUsuario, String role){
        Optional<PublicacaoModel> publicacaoModel = publicacaoRepository.findById(id);

        if(publicacaoModel.isEmpty()){
            throw new CustomException("Id não existente.",HttpStatus.NOT_FOUND);
        }else{
            if(role.equals("ROLE_ADMIN")){
                publicacaoRepository.deleteById(id);
            }else if(publicacaoModel.get().getUsuario().equals(nomeUsuario)) {
                publicacaoRepository.deleteById(id);
            }else{
                throw  new CustomException("Você não pode excluir publicações de outros usuários.", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
        }
    }
}
