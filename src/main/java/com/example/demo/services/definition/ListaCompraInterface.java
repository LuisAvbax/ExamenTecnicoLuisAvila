package com.example.demo.services.definition;

import com.example.demo.dto.ListaCompraDto;
import com.example.demo.entities.ListaCompra;

import java.util.List;

public interface ListaCompraInterface {

    ListaCompra crearListaCompra(ListaCompraDto listaCompraDto);
    List<ListaCompra> obtenerListasPorCliente(Integer clienteId);
    ListaCompra actualizarListaCompra(Integer listaCompraId, ListaCompraDto listaCompraDto);
    void eliminarListaCompra(Integer listaCompraId);
}
