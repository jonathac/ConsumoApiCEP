package br.com.jonatha.consumo.api.resources;

import br.com.jonatha.consumo.api.domain.Endereco;
import br.com.jonatha.consumo.api.dto.CepDTO;
import br.com.jonatha.consumo.api.services.CepService;
import br.com.jonatha.consumo.api.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cep")
public class EnderecoResource {

    @Autowired
    EnderecoService service;

    @Autowired
    CepService cepService;

    //CREATE
    @PostMapping()
    public ResponseEntity<Endereco> insert(@Valid @RequestBody CepDTO objDto) {
        Endereco obj = service.cepFromDto(objDto);
        obj = cepService.buscaEndereco(obj.getCep());
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //READ
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Optional<Endereco> obj = service.findById(id);
        if (obj.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok().body(obj.get());
    }
    //READ ALL
    @GetMapping()
    public ResponseEntity<List<Endereco>> findAll (){
        List<Endereco> list = new ArrayList<>();
        list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //UPDATE

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        Optional<Endereco> obj = service.findById(id);
        if (obj.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
