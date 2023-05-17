package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    @Query("From Modelo where ativo = :ativo")
    public List<Modelo> findByAtivo(@Param("ativo")final boolean ativo);

    @Query("SELECT v FROM Veiculo v WHERE v.modelo.id = :modeloId")
    Optional<Veiculo> findByModeloId(@Param("modeloId") Long modeloId);


}
