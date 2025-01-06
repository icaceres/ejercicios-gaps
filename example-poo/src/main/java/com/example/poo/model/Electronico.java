package com.example.poo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Electronico extends Producto {

    private Double voltaje;
}
