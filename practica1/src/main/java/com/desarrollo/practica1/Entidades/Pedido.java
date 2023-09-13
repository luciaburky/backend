package com.desarrollo.practica1.Entidades;
import com.desarrollo.practica1.Enumeraciones.EstadoPedido;
import com.desarrollo.practica1.Enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Pedido")
public class Pedido extends BaseEntidad {
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private double total;
    private EstadoPedido estadoPedido;
    private TipoEnvio tipoEnvio;

    // Relación OneToOne entre Pedido y Factura
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "factura_id")
    private Factura factura;

    // Relación OneToMany entre Pedido y DetallePedido
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn (name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    public void agregarDetalles (DetallePedido detalle) {
        detalles.add(detalle);
    }

    public void mostrarDetalles () {
        System.out.println("Detalles del pedido:");
        for (DetallePedido detallePedido : detalles) {
            System.out.println(detallePedido.getCantidad() + " " + detallePedido.getProducto() + " " + detallePedido.getSubtotal());
        }
    }



}
