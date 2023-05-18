package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {
    private final ModeloRepository modeloRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    public List<Modelo> listarModelos() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> buscarModeloPorId(Long id) {
        return modeloRepository.findById(id);
    }

    public Modelo criarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public Modelo atualizarModelo(Long id, Modelo modeloAtualizado) {
        Optional<Modelo> modeloExistente = modeloRepository.findById(id);

        if (modeloExistente.isPresent()) {
            Modelo modelo = modeloExistente.get();
            modelo.setNomeModelo(modeloAtualizado.getNomeModelo());
            modelo.setMarca(modeloAtualizado.getMarca());
            return modeloRepository.save(modelo);
        } else {
            throw new RuntimeException("Modelo não encontrado com o ID: " + id);
        }
    }

    public void deletarModelo(Long id) {
        modeloRepository.deleteById(id);
    }
}
