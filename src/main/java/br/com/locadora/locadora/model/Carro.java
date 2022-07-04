package br.com.locadora.locadora.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "carro", schema = "locadora")
public class Carro {

    @ApiModelProperty(value = "Código do carro")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Marca do carro")
    @JoinColumn(name = "marca")
    private String marca;

    @ApiModelProperty(value = "Modelo do carro")
    private String modelo;

    @ApiModelProperty(value = "Disponibilidade do carro")
    private Boolean disponivel;
}
