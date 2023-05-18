package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> buscarMovimentacaoPorId(Long id) {
        return movimentacaoRepository.findById(id);
    }

    public Movimentacao criarMovimentacao(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public Movimentacao atualizarMovimentacao(Long id, Movimentacao movimentacaoAtualizada) {
        Optional<Movimentacao> movimentacaoExistente = movimentacaoRepository.findById(id);

        if (movimentacaoExistente.isPresent()) {
            Movimentacao movimentacao = movimentacaoExistente.get();
            movimentacao.setCondutor(movimentacaoAtualizada.getCondutor());
            movimentacao.setVeiculo(movimentacaoAtualizada.getVeiculo());
            movimentacao.setDataEntrada(movimentacaoAtualizada.getDataEntrada());
            movimentacao.setDataSaida(movimentacaoAtualizada.getDataSaida());
            movimentacao.setTempo(movimentacaoAtualizada.getTempo());
            movimentacao.setTempoDesconto(movimentacaoAtualizada.getTempoDesconto());
            movimentacao.setTempoMulta(movimentacaoAtualizada.getTempoMulta());
            movimentacao.setValorDesconto(movimentacaoAtualizada.getValorDesconto());
            movimentacao.setValorHora(movimentacaoAtualizada.getValorHora());
            movimentacao.setValorTotal(movimentacaoAtualizada.getValorTotal());
            movimentacao.setValorMulta(movimentacaoAtualizada.getValorMulta());
            movimentacao.setValorHoraMulta(movimentacaoAtualizada.getValorHoraMulta());
            return movimentacaoRepository.save(movimentacao);
        } else {
            throw new RuntimeException("Movimentação não encontrada com o ID: " + id);
        }
    }

    public void deletarMovimentacao(Long id) {
        movimentacaoRepository.deleteById(id);
    }
}
