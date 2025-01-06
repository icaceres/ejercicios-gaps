package com.example.poo.service;

import com.example.poo.model.Producto;

import java.util.List;

public interface ProductoService {

    void agregarProducto(Producto producto);

    List<Producto> listarProductos();
}
