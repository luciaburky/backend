package com.desarrollo.practica1.Entidades;
import com.desarrollo.practica1.Enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Factura")
public class Factura extends BaseEntidad {
    private int numero;
    private Date fecha;
    private double descuento;
    private double total; // el diagrama decía int, cambio por double
    private FormaPago formaPago;

}
