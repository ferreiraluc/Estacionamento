package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.entity.Veiculo;
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
    private ModeloRepository modeloRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Modelo> modelo = modeloRepository.findById(id);

        return modelo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ativo/{ativo}")
    public ResponseEntity<?> findByAtivo(@PathVariable boolean ativo) {
        List<Modelo> modelos = modeloRepository.findByAtivo(ativo);

        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(modelos);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Modelo> modelos = modeloRepository.findAll();

        if (modelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(modelos);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Modelo modelo) {
        modeloRepository.save(modelo);
        return ResponseEntity.ok().body("Registro cadastrado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final @NotNull Long id, @RequestBody final Modelo modelo) {
        if (id.equals(modelo.getId()) && modeloRepository.existsById(id)) {
            modeloRepository.save(modelo);
            return ResponseEntity.ok().body("Registro atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Id não foi encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Modelo> optionalModelo = modeloRepository.findById(id);
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findByModeloId(id);

        if (optionalModelo.isPresent() && optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            Modelo modelo = optionalModelo.get();
            Modelo modeloVeiculo = veiculo.getModelo();
            Movimentacao movimentacao = veiculo.getMovimentacao();

            if (movimentacao.isAtivo() && !modelo.equals(modeloVeiculo)) {
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
