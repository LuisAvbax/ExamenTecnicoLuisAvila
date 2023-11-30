package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaCompraDetalleId implements Serializable {

    @Column(name = "idListaCompra")
    private Integer idListaCompra;

    @Column(name = "idCodigoProducto")
    private Integer idCodigoProducto;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idListaCompra == null) ? 0 : idListaCompra.hashCode());
        result = prime * result + ((idCodigoProducto == null) ? 0 : idCodigoProducto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ListaCompraDetalleId other = (ListaCompraDetalleId) obj;
        if (idListaCompra == null) {
            if (other.idListaCompra != null) {
                return false;
            }
        } else if (!idListaCompra.equals(other.idListaCompra)) {
            return false;
        }
        if (idCodigoProducto == null) {
            if (other.idCodigoProducto != null) {
                return false;
            }
        } else if (!idCodigoProducto.equals(other.idCodigoProducto)) {
            return false;
        }
        return true;
    }

}
