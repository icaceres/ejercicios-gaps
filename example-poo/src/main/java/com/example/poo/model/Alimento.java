package com.example.poo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Alimento extends Producto{

    private LocalDate fechaCaducidad;
    private String infoNutricional;
}
