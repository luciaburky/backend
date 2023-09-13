package com.desarrollo.practica1.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Domicilio")
public class Domicilio extends BaseEntidad {
    private String calle;
    private String numero;
    private String localidad;
}
