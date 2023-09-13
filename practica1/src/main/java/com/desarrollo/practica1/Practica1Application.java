package com.desarrollo.practica1;
import com.desarrollo.practica1.Entidades.*;
import com.desarrollo.practica1.Enumeraciones.EstadoPedido;
import com.desarrollo.practica1.Enumeraciones.FormaPago;
import com.desarrollo.practica1.Enumeraciones.TipoEnvio;
import com.desarrollo.practica1.Enumeraciones.TipoProducto;
import com.desarrollo.practica1.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Practica1Application {
	// Inyecciones de dependencia
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	FacturaRepository facturaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Practica1Application.class, args);
	}

    @Bean
	CommandLineRunner init (RubroRepository rubroRepository, ClienteRepository clienteRepository, PedidoRepository pedidoRepository, DomicilioRepository domicilioRepository, ProductoRepository productoRepository, DetallePedidoRepository detallePedidoRepository, FacturaRepository facturaRepository) {
		return args -> {
			System.out.println("Funciono bien");

			// LO USO PARA LAS FECHAS DE PEDIDO Y FACTURA
			SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString1 = "2023-09-18";
			String fechaString2 = "2023-08-19";
			Date fechaPedido1 = fechaFormato.parse(fechaString1);
			Date fechaPedido2 = fechaFormato.parse(fechaString2);

			// CREO LOS PRODUCTOS
			Producto producto1 = Producto.builder()
					.denominacion("Pizza Margarita")
					.unidadMedida("1 unidad")
					.precioCompra(2000)
					.precioVenta(3500)
					.receta("Colocar 300 gramos de masa madre con harina y agua, mezclar, estirar y agregar salsa, queso y tomates")
					.stockActual(80)
					.stockMinimo(20)
					.tipoProducto(TipoProducto.MANUFACTURADO)
					.tiempoEstimadoCocina(30)
					.build();
			Producto producto2 = Producto.builder()
					.denominacion("Pizza Italiana")
					.unidadMedida("1 unidad")
					.precioCompra(2000)
					.precioVenta(4500)
					.receta("Colocar 300 gramos de masa madre con harina y agua, mezclar, estirar y agregar salsa, queso y champiñones")
					.stockActual(120)
					.stockMinimo(20)
					.tipoProducto(TipoProducto.MANUFACTURADO)
					.tiempoEstimadoCocina(30)
					.build();

			// CREO EL RUBRO
			Rubro rubro1 = Rubro.builder()
					.denominacion("Pizza")
					.build();

			// AGREGO PRODUCTOS A RUBRO
			rubro1.agregarProductos(producto1);
			rubro1.agregarProductos(producto2);
			// Y GUARDO EL RUBRO
			rubroRepository.save(rubro1);

			// CREO DETALLES DEL PEDIDO
			DetallePedido detalle1 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(4500)
					.build();
			DetallePedido detalle2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(3500)
					.build();
			DetallePedido detalle3 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(7000)
					.build();
			// GUARDO PRODUCTO EN DETALLE
			detalle1.setProducto(producto2);
			detalle2.setProducto(producto1);
			detalle3.setProducto(producto1);

			// CREO PEDIDOS
			Pedido pedido1 = Pedido.builder()
					.estadoPedido(EstadoPedido.ENTREGADO)
					.fecha(fechaPedido1)
					.tipoEnvio(TipoEnvio.RETIRO)
					.total(8000)
					.build();
			Pedido pedido2 = Pedido.builder()
					.estadoPedido(EstadoPedido.PREPARACION)
					.fecha(fechaPedido2)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(7000)
					.build();

			// AGREGO DETALLES A PEDIDOS
			pedido1.agregarDetalles(detalle1);
			pedido1.agregarDetalles(detalle2);
			pedido2.agregarDetalles(detalle3);

			// CREO FACTURAS
			Factura factura1 = Factura.builder()
					.numero(34)
					.fecha(fechaPedido1)
					.descuento(200)
					.total(7800)
					.formaPago(FormaPago.MERCADOPAGO)
					.build();
			Factura factura2 = Factura.builder()
					.numero(33)
					.fecha(fechaPedido2)
					.descuento(0)
					.total(7000)
					.formaPago(FormaPago.CREDITO)
					.build();

			// GUARDO FACTURA EN PEDIDO
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			// CREO DOMICILIOS
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Retamales")
					.numero("901")
					.localidad("Lujan de Cuyo")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Huarpes")
					.numero("192")
					.localidad("Ciudad")
					.build();
			Domicilio domicilio3 = Domicilio.builder()
					.calle("Beggi")
					.numero("392")
					.localidad("San Martin")
					.build();

			// CREO CLIENTES
			Cliente cliente1 = Cliente.builder()
					.nombre("Sebastian")
					.apellido("Kliak")
					.email("sebaakliak@gmail.com")
					.telefono("2634732890")
					.build();
			Cliente cliente2 = Cliente.builder()
					.nombre("Lucia")
					.apellido("Iripo")
					.email("luciairipo@gmail.com")
					.telefono("2612994910")
					.build();

			// AGREGO DOMICILIOS A CLIENTES
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio3);
			cliente2.agregarDomicilio(domicilio2);

			// AGREGO PEDIDOS A CLIENTES
			cliente1.agregarPedidos(pedido1);
			cliente2.agregarPedidos(pedido2);

			// GUARDO CLIENTES
			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);

			// CHEQUEO QUE ME DEVUELVA EL/LOS DOMICILIO/S DE CADA CLIENTE
			Cliente clienteBuscado1 = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteBuscado1 != null) {
				System.out.println("Nombre: " + clienteBuscado1.getNombre());
				System.out.println("Apellido: " + clienteBuscado1.getApellido());
				System.out.println("Telefono: " + clienteBuscado1.getTelefono());
				System.out.println("Email: " + clienteBuscado1.getEmail());
				clienteBuscado1.mostrarDomicilios();
			}
			Cliente clienteBuscado2 = clienteRepository.findById(cliente2.getId()).orElse(null);
			if (clienteBuscado2 != null) {
				System.out.println("Nombre: " + clienteBuscado2.getNombre());
				System.out.println("Apellido: " + clienteBuscado2.getApellido());
				System.out.println("Telefono: " + clienteBuscado2.getTelefono());
				System.out.println("Email: " + clienteBuscado2.getEmail());
				clienteBuscado2.mostrarDomicilios();
			}

		};
	}
}
