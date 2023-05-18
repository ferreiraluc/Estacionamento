package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);

        if (veiculoExistente.isPresent()) {
            Veiculo veiculo = veiculoExistente.get();
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setCor(veiculoAtualizado.getCor());
            veiculo.setTipo(veiculoAtualizado.getTipo());
            veiculo.setAnoModelo(veiculoAtualizado.getAnoModelo());
            veiculo.setMovimentacao(veiculoAtualizado.getMovimentacao());
            return veiculoRepository.save(veiculo);
        } else {
            throw new RuntimeException("Veículo não encontrado com o ID: " + id);
        }
    }

    public void deletarVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}
