package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {
    private final ModeloService modeloService;

    @Autowired
    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public ResponseEntity<List<Modelo>> listarModelos() {
        List<Modelo> modelos = modeloService.listarModelos();
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> buscarModeloPorId(@PathVariable Long id) {
        Optional<Modelo> modelo = modeloService.buscarModeloPorId(id);
        return modelo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Modelo> criarModelo(@RequestBody Modelo modelo) {
        Modelo novoModelo = modeloService.criarModelo(modelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoModelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> atualizarModelo(@PathVariable Long id, @RequestBody Modelo modeloAtualizado) {
        Modelo modelo = modeloService.atualizarModelo(id, modeloAtualizado);
        return ResponseEntity.ok(modelo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarModelo(@PathVariable Long id) {
        modeloService.deletarModelo(id);
        return ResponseEntity.noContent().build();
    }
}
