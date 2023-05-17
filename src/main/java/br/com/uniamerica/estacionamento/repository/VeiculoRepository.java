package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("From Veiculo where ativo = :ativo")
    public List<Veiculo> findByAtivo(@Param("ativo")final boolean ativo);

    Optional<Veiculo> findByModeloId(Long modeloId);
}
