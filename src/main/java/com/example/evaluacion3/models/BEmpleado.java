package com.example.evaluacion3.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleado")
public class BEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ocupacion;
    private Integer horasTrabajadas;
    private Double pagoPorHora;

    // Método para obtener el total basado en horas trabajadas y pago por hora
    public Double obtenerTotal() {
        if (horasTrabajadas != null && pagoPorHora != null) {
            return horasTrabajadas * pagoPorHora;
        }
        return 0.0;
    }

    // Método para calcular el descuento AFP (10%)
    public Double descuentoAfp() {
        return obtenerTotal() * 0.10; // Descuento del 10%
    }

    // Método para obtener el pago neto después de aplicar el descuento AFP
    public Double obtenerNeto() {
        return obtenerTotal() - descuentoAfp();
    }
}