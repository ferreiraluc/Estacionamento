package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> buscarMarcaPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public Marca criarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca atualizarMarca(Long id, Marca marcaAtualizada) {
        Optional<Marca> marcaExistente = marcaRepository.findById(id);

        if (marcaExistente.isPresent()) {
            Marca marca = marcaExistente.get();
            marca.setNomeMarca(marcaAtualizada.getNomeMarca());
            return marcaRepository.save(marca);
        } else {
            throw new RuntimeException("Marca não encontrada com o ID: " + id);
        }
    }

    public void deletarMarca(Long id) {
        marcaRepository.deleteById(id);
    }
}
