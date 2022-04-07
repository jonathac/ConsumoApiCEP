package br.com.jonatha.consumo.api.resources;

import br.com.jonatha.consumo.api.domain.Endereco;
import br.com.jonatha.consumo.api.services.CepService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class CepResources {

    @Autowired
    CepService cepService;

    @ApiOperation("Busca endero por cep na URL")
    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> buscarCep(@PathVariable String cep){
        Endereco obj = cepService.buscaEndereco(cep);
        return ResponseEntity.ok().body(obj);
    }

}
