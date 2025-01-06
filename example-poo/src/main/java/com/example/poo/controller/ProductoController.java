package com.example.poo.controller;

import com.example.poo.model.Alimento;
import com.example.poo.model.Electronico;
import com.example.poo.model.Producto;
import com.example.poo.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("alimento")
    public void agregarProductoAlimento(@RequestBody Alimento alimento) {
        productoService.agregarProducto(alimento);
    }

    @PostMapping("electronico")
    public void agregarProductoElectronico(@RequestBody Electronico electronico) {
        productoService.agregarProducto(electronico);
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }
}
