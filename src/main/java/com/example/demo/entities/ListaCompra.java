package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ListaCompras")
public class ListaCompra {
    @Id
    @Column(name = "idLista", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLista;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "fechaRegistro")
    private Date fechaRegistro;

    @Column(name = "fechaUltimaActualizacion")
    private Date fechaUltimaActualizacion;

    @Column(name = "activo")
    private Boolean activo;

    @OneToMany(mappedBy = "listaCompra")
    private Set<ListaCompraDetalle> detalles;

}
