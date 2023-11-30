package com.example.demo.controller;

import com.example.demo.dto.ListaCompraDto;
import com.example.demo.entities.ListaCompra;
import com.example.demo.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.definition.ListaCompraInterface;

import java.util.List;

@RestController
@RequestMapping("/api/listasCompras")
public class ListaCompraController {

    @Autowired
    private ListaCompraInterface listaCompraService;

    @Autowired
    JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<ListaCompra> crearListaCompra(HttpServletRequest request,
                                                        @RequestBody ListaCompraDto listaCompraDto) {

        if(!jwtService.verifyToken(request.getHeader("Authorization"))) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ListaCompra listaCreada = listaCompraService.crearListaCompra(listaCompraDto);
        return new ResponseEntity<>(listaCreada, HttpStatus.CREATED);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<ListaCompra>> obtenerListasPorCliente(HttpServletRequest request, @PathVariable Integer clienteId) {
        if(!jwtService.verifyToken(request.getHeader("Authorization"))) {
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<ListaCompra> listas = listaCompraService.obtenerListasPorCliente(clienteId);
        return new ResponseEntity<>(listas, HttpStatus.OK);
    }

    @PutMapping("/{listaCompraId}")
    public ResponseEntity<ListaCompra> actualizarListaCompra(HttpServletRequest request, @PathVariable Integer listaCompraId,
                                                             @RequestBody ListaCompraDto listaCompraDto) {
        if(!jwtService.verifyToken(request.getHeader("Authorization"))) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ListaCompra listaActualizada = listaCompraService.actualizarListaCompra(listaCompraId, listaCompraDto);
        return new ResponseEntity<>(listaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{listaCompraId}")
    public ResponseEntity<Void> eliminarListaCompra(HttpServletRequest request, @PathVariable Integer listaCompraId) {
        if(!jwtService.verifyToken(request.getHeader("Authorization"))) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        listaCompraService.eliminarListaCompra(listaCompraId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
