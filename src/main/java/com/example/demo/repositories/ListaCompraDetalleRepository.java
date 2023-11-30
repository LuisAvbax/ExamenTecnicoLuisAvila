package com.example.demo.repositories;

import com.example.demo.entities.ListaCompra;
import com.example.demo.entities.ListaCompraDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaCompraDetalleRepository extends JpaRepository <ListaCompraDetalle, Integer> {
    void deleteByListaCompra(ListaCompra listaCompra);
}
