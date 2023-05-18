package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    private final MarcaService marcaService;

    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listarMarcas() {
        List<Marca> marcas = marcaService.listarMarcas();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarMarcaPorId(@PathVariable Long id) {
        Optional<Marca> marca = marcaService.buscarMarcaPorId(id);
        return marca.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Marca> criarMarca(@RequestBody Marca marca) {
        Marca novaMarca = marcaService.criarMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizarMarca(@PathVariable Long id, @RequestBody Marca marcaAtualizada) {
        Marca marca = marcaService.atualizarMarca(id, marcaAtualizada);
        return ResponseEntity.ok(marca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMarca(@PathVariable Long id) {
        marcaService.deletarMarca(id);
        return ResponseEntity.noContent().build();
    }
}
