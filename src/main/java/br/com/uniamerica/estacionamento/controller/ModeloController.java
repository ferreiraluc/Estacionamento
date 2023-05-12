package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.*;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/modelo")
public class ModeloController {

    @Autowired
    ModeloRepository modeloRepository;

    @Autowired
    VeiculoRepository veiculoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.modeloRepository.findById(id));
    }

    @GetMapping("/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo){
        List<Modelo> modelos = this.modeloRepository.findByAtivo(ativo);

        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(modelos);
    }
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Modelo> modelos = this.modeloRepository.findAll();

        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(modelos);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Modelo modelo) {
        this.modeloRepository.save(modelo);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody final Modelo modelo) {
        if (id.equals(modelo.getId()) && !this.modeloRepository.findById(id).isEmpty()) {
            this.modeloRepository.save(modelo);
        } else {
            return ResponseEntity.badRequest().body("Id nao foi encontrado");
        }
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Modelo> optionalModelo = modeloRepository.findById(id);
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);

        if (optionalModelo.isPresent() && optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            Modelo modelo = optionalModelo.get();
            Modelo modelov = veiculo.getModelo();
            Movimentacao movimentacao = veiculo.getMovimentacao();

            if (movimentacao.isAtivo() && modelo != (modelov)) {
                modeloRepository.delete(modelo);
                return ResponseEntity.ok("O registro do modelo foi deletado com sucesso");
            } else {
                modelo.setAtivo(false);
                modeloRepository.save(modelo);
                return ResponseEntity.ok("O modelo estava vinculado a uma ou mais movimentações e foi desativado com sucesso");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
