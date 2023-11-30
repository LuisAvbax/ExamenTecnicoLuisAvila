package com.example.demo.services.implementation;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Producto;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.ListaCompraDetalleRepository;
import com.example.demo.repositories.ListaCompraRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.dto.ListaCompraDto;
import com.example.demo.entities.ListaCompra;
import com.example.demo.entities.ListaCompraDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.services.definition.ListaCompraInterface;

import java.util.Date;
import java.util.List;

@Service
public class ListaCompraServicempl implements ListaCompraInterface {

    @Autowired
    ListaCompraDetalleRepository listaCompraDetalleRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ListaCompraRepository listaCompraRepository;

    /**
     * @param listaCompraDto
     * @return
     */
    @Override
    public ListaCompra crearListaCompra(ListaCompraDto listaCompraDto) {
        Cliente cliente = clienteRepository.findById(listaCompraDto.getIdCliente())
                .orElseGet(() -> {
                    Cliente nuevoCliente = new Cliente();
                    nuevoCliente.setIdCliente(listaCompraDto.getIdCliente());
                    // Establecer otros campos del cliente según sea necesario
                    return clienteRepository.save(nuevoCliente);
                });

        ListaCompra nuevaLista = new ListaCompra();
        nuevaLista.setCliente(cliente);
        nuevaLista.setNombre(listaCompraDto.getNombreLista());
        nuevaLista.setFechaRegistro(new Date()); // Fecha actual
        nuevaLista.setActivo(true);

        // Recibimos la entidad guardada
        ListaCompra listaGuardada = listaCompraRepository.save(nuevaLista);

        // Iteramos sobre los detalles de la lista recibida en el DTO
        listaCompraDto.getDetalles().forEach(detalleDto -> {
            Producto producto = productoRepository.findById(detalleDto.getIdProducto())
                    .orElseGet(() -> {
                        Producto nuevoProducto = new Producto();
                        nuevoProducto.setIdProducto(detalleDto.getIdProducto());
                        // Establecer otros campos del producto según sea necesario
                        return productoRepository.save(nuevoProducto);
                    });

            ListaCompraDetalle detalle = new ListaCompraDetalle();
            // ... (resto del código igual)
        });

        return listaGuardada;
    }

    /**
     * @param clienteId
     * @return
     */
    @Override
    public List<ListaCompra> obtenerListasPorCliente(Integer clienteId) {
        return listaCompraRepository.findByClienteIdCliente(clienteId);
    }

    /**
     * @param listaCompraId
     * @param listaCompraDto
     * @return
     */
    @Override
    public ListaCompra actualizarListaCompra(Integer listaCompraId, ListaCompraDto listaCompraDto) {
        ListaCompra listaCompra = listaCompraRepository.findById(listaCompraId)
                .orElseThrow(() -> new RuntimeException("Lista de compras no encontrada"));

        listaCompra.setNombre(listaCompraDto.getNombreLista());
        listaCompra.setFechaUltimaActualizacion(new Date()); // Fecha actual

        // Eliminar detalles existentes
        listaCompraDetalleRepository.deleteByListaCompra(listaCompra);

        // Agregar los nuevos detalles
        listaCompraDto.getDetalles().forEach(detalleDto -> {
            Producto producto = productoRepository.findById(detalleDto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            ListaCompraDetalle detalle = new ListaCompraDetalle();
            detalle.setListaCompra(listaCompra);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDto.getCantidad());

            listaCompraDetalleRepository.save(detalle);
        });

        return listaCompraRepository.save(listaCompra);
    }

    /**
     * @param listaCompraId
     */
    @Override
    public void eliminarListaCompra(Integer listaCompraId) {
        // Buscamos si existe la lista de compras
        ListaCompra listaCompra = listaCompraRepository.findById(listaCompraId)
                // Si no existe lanzamos una excepcion
                .orElseThrow(() -> new RuntimeException("Lista de compras no encontrada"));

        // Eliminamos la lista
        listaCompraRepository.delete(listaCompra);
    }
}
