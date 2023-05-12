package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondutorService {

    private final CondutorRepository condutorRepository;

    @Autowired
    public CondutorService(CondutorRepository condutorRepository) {
        this.condutorRepository = condutorRepository;
    }

    public Optional<Condutor> findById(Long id) {
        return condutorRepository.findById(id);
    }

    public List<Condutor> findByAtivo(boolean ativo) {
        return condutorRepository.findByAtivo(ativo);
    }

    public List<Condutor> findAll() {
        return condutorRepository.findAll();
    }

    public Condutor save(Condutor condutor) {
        return condutorRepository.save(condutor);
    }

    public Optional<Condutor> update(Long id, Condutor condutor) {
        Optional<Condutor> optionalCondutor = condutorRepository.findById(id);

        if (optionalCondutor.isPresent()) {
            Condutor existingCondutor = optionalCondutor.get();
            existingCondutor.setNomeCondutor(condutor.getNomeCondutor());
            existingCondutor.setCpf(condutor.getCpf());
            existingCondutor.setTelefone(condutor.getTelefone());
            existingCondutor.setTempoDesconto(condutor.getTempoDesconto());
            existingCondutor.setTempoPago(condutor.getTempoPago());
            existingCondutor.setMovimentacao(condutor.getMovimentacao());

            return Optional.of(condutorRepository.save(existingCondutor));
        }

        return Optional.empty();
    }

    public void delete(Long id) {
        Optional<Condutor> optionalCondutor = condutorRepository.findById(id);

        if (optionalCondutor.isPresent()) {
            Condutor condutor = optionalCondutor.get();

            if (condutor.getMovimentacao().isAtivo()) {
                condutorRepository.delete(condutor);
            } else {
                condutor.setAtivo(false);
                condutorRepository.save(condutor);
            }
        }
    }
}
