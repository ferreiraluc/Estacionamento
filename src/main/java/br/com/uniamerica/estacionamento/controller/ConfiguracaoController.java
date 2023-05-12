package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.service.ConfiguracaoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/configuracao")
public class ConfiguracaoController {

    private final ConfiguracaoService configuracaoService;

    @Autowired
    public ConfiguracaoController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(configuracaoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Configuracao> configuracoes = configuracaoService.findAll();

        if (configuracoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(configuracoes);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Configuracao configuracao) {
        configuracaoService.save(configuracao);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody final Configuracao configuracao) {
        Optional<Configuracao> updatedConfiguracao = configuracaoService.update(id, configuracao);

        if (updatedConfiguracao.isPresent()) {
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Id não foi encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable final Long id) {
        configuracaoService.delete(id);
        return ResponseEntity.ok().body("O registro da configuração foi deletado com sucesso");
    }
}
