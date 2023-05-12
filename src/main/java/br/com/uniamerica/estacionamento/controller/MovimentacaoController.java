package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.movimentacaoRepository.findById(id));
    }

    @GetMapping("/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo){
        List<Movimentacao> movimentacaos = this.movimentacaoRepository.findByAtivo(ativo);

        if (movimentacaos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(movimentacaos);
    }
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Movimentacao> movimentacaos = this.movimentacaoRepository.findAll();

        if (movimentacaos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(movimentacaos);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Movimentacao movimentacao) {
        this.movimentacaoRepository.save(movimentacao);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody final Movimentacao movimentacao) {
        if (id.equals(movimentacao.getId()) && !this.movimentacaoRepository.findById(id).isEmpty()) {
            this.movimentacaoRepository.save(movimentacao);
        } else {
            return ResponseEntity.badRequest().body("Id nao foi encontrado");
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }
}
