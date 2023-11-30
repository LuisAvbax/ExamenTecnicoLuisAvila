package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "ListaCompraDetalle")
public class ListaCompraDetalle {
    @EmbeddedId
    private ListaCompraDetalleId id;

    @MapsId("idListaCompra")
    @ManyToOne
    @JoinColumn(name = "idListaCompra", nullable = false)
    private ListaCompra listaCompra;

    @MapsId("idCodigoProducto")
    @ManyToOne
    @JoinColumn(name = "idCodigoProducto", nullable = false)
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}
