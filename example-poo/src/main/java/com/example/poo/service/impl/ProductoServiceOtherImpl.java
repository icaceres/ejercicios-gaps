package com.example.poo.service.impl;

import com.example.poo.model.Alimento;
import com.example.poo.model.Producto;
import com.example.poo.service.ProductoService;

import java.util.List;

public class ProductoServiceOtherImpl implements ProductoService {
    @Override
    public void agregarProducto(Producto producto) {
        if(producto instanceof Alimento) {
            System.out.println("Se ingresa un alimento");
        } else {
            System.out.println("Se ingresa un electronico");
        }
    }

    @Override
    public List<Producto> listarProductos() {
        return List.of();
    }
}
