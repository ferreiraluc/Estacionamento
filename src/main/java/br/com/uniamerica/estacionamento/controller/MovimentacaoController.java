package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;

    @Autowired
    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarMovimentacoes() {
        List<Movimentacao> movimentacoes = movimentacaoService.listarMovimentacoes();
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> buscarMovimentacaoPorId(@PathVariable Long id) {
        Optional<Movimentacao> movimentacao = movimentacaoService.buscarMovimentacaoPorId(id);
        return movimentacao.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movimentacao> criarMovimentacao(@RequestBody Movimentacao movimentacao) {
        Movimentacao novaMovimentacao = movimentacaoService.criarMovimentacao(movimentacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMovimentacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> atualizarMovimentacao(@PathVariable Long id, @RequestBody Movimentacao movimentacaoAtualizada) {
        Movimentacao movimentacao = movimentacaoService.atualizarMovimentacao(id, movimentacaoAtualizada);
        return ResponseEntity.ok(movimentacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMovimentacao(@PathVariable Long id) {
        movimentacaoService.deletarMovimentacao(id);
        return ResponseEntity.noContent().build();
    }
}
