package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "Clientes")
public class Cliente {
    @Id
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "cliente")
    private Set<ListaCompra> listasCompra;

}
