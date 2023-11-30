package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaCompraDto {
    private Integer idCliente;
    private String nombreLista;
    private List<DetalleListaCompraDto> detalles;
}