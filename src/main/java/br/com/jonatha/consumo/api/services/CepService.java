package br.com.jonatha.consumo.api.services;

import br.com.jonatha.consumo.api.domain.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://viacep.com.br/ws/" , name = "ConsultaCep")
public interface CepService {

    @GetMapping("{cep}/json")
    Endereco buscaEndereco(@PathVariable("cep") String cep);
}