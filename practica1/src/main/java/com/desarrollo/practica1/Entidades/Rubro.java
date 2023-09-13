package com.desarrollo.practica1.Entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "Rubro")
public class Rubro extends BaseEntidad {
    private String denominacion;

    // Relación OneToMany entre Rubro y Producto
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn (name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProductos (Producto producto) {
        productos.add(producto);
    }
    public void mostrarProductos () {
        System.out.println("Productos del rubro " + denominacion + ": ");
        for (Producto producto : productos) {
            System.out.println(producto.getDenominacion());
        }
    }

}
