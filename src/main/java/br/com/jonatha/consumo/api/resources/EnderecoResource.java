package br.com.jonatha.consumo.api.resources;

import br.com.jonatha.consumo.api.domain.Endereco;
import br.com.jonatha.consumo.api.dto.CepDTO;
import br.com.jonatha.consumo.api.services.CepService;
import br.com.jonatha.consumo.api.services.EnderecoService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("Busca endereco por cep no body")
    @PostMapping()
    public ResponseEntity<Endereco> insert(@Valid @RequestBody CepDTO objDto) {
        Endereco obj = service.cepFromDto(objDto);
        obj = cepService.buscaEndereco(obj.getCep());
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //READ
    @ApiOperation("Realiza busca no BD por id das consulta anteriores")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        Optional<Endereco> obj = service.findById(id);
        if (obj.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok().body(obj.get());
    }
    //READ ALL
    @ApiOperation("Realiza busca no BD de todas consulta anteriores")
    @GetMapping()
    public ResponseEntity<List<Endereco>> findAll (){
        List<Endereco> lista = new ArrayList<>();
        lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    //UPDATE

    //DELETE
    @ApiOperation("Exclui consulta por id no BDlo")
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
