package br.com.uniamerica.estacionamento.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "modelos", schema = "public")
public class Modelo extends AbstractEntity {
    @Getter @Setter
    @Column(name = "nome_modelo", nullable = false, unique = true, length = 50)
    private String nomeModelo;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

}
