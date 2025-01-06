package com.example.poo.service.impl;

import com.example.poo.model.Producto;
import com.example.poo.service.ProductoService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class ProductoServiceImpl implements ProductoService {


    List<Producto> productos = new ArrayList<>();


    @Override
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productos;
    }
}
