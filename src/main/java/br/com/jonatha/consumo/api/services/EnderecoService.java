package br.com.jonatha.consumo.api.services;

import br.com.jonatha.consumo.api.domain.Endereco;
import br.com.jonatha.consumo.api.dto.CepDTO;
import br.com.jonatha.consumo.api.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repo;

    //CREATE
    public Endereco insert (Endereco obj){
        return repo.save(obj);
    }
    //READ
    public Optional<Endereco> findById(Integer id){
        Optional<Endereco> obj = repo.findById(id);
        return obj;
    }

    //READ ALL
    public List<Endereco> findAll(){
        List<Endereco> list = new ArrayList<>();
        list = repo.findAll();
        return list;
    }
    //UPDATE

    //DELETE
    public void delete (Integer id){
        repo.deleteById(id);
    }

    //FROM DTO
    public Endereco cepFromDto (CepDTO objDto){
        Endereco obj = new Endereco();
        obj.setCep(objDto.getCep());
        return obj;
    }

}
