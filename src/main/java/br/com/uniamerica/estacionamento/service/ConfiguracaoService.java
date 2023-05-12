package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoService {

    private final ConfiguracaoRepository configuracaoRepository;

    @Autowired
    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

    public Optional<Configuracao> findById(Long id) {
        return configuracaoRepository.findById(id);
    }

    public List<Configuracao> findAll() {
        return configuracaoRepository.findAll();
    }

    public Configuracao save(Configuracao configuracao) {
        return configuracaoRepository.save(configuracao);
    }

    public Optional<Configuracao> update(Long id, Configuracao configuracao) {
        Optional<Configuracao> optionalConfiguracao = configuracaoRepository.findById(id);

        if (optionalConfiguracao.isPresent()) {
            Configuracao existingConfiguracao = optionalConfiguracao.get();
            existingConfiguracao.setValorHora(configuracao.getValorHora());
            existingConfiguracao.setValorMinutoMulta(configuracao.getValorMinutoMulta());
            existingConfiguracao.setInicioExpediente(configuracao.getInicioExpediente());
            existingConfiguracao.setFimExpediente(configuracao.getFimExpediente());
            existingConfiguracao.setTempoParaDesconto(configuracao.getTempoParaDesconto());
            existingConfiguracao.setTempoDeDesconto(configuracao.getTempoDeDesconto());
            existingConfiguracao.setGerarDesconto(configuracao.isGerarDesconto());
            existingConfiguracao.setVagasMoto(configuracao.getVagasMoto());
            existingConfiguracao.setVagasCarro(configuracao.getVagasCarro());
            existingConfiguracao.setVagasVan(configuracao.getVagasVan());

            return Optional.of(configuracaoRepository.save(existingConfiguracao));
        }

        return Optional.empty();
    }

    public void delete(Long id) {
        configuracaoRepository.deleteById(id);
    }
}
