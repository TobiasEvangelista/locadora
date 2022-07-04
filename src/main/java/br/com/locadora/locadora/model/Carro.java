package br.com.locadora.locadora.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carro")
public class Carro {

    @ApiModelProperty(value = "Código do carro")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Marca do carro")
    private String marca;

    @ApiModelProperty(value = "Modelo do carro")
    private String modelo;

    @ApiModelProperty(value = "Disponibilidade do carro")
    private Boolean disponivel;
}
