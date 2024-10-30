import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

class Producto {
    private String nombre;
    private int cantidad;

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return nombre + " (Cantidad: " + cantidad + ")";
    }
}

class Pedido {
    private String nombreCliente;
    private LinkedList<Producto> productos;

    public Pedido(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.productos = new LinkedList<>();
    }

    public void agregarProducto(String nombreProducto, int cantidad) {
        productos.add(new Producto(nombreProducto, cantidad));
    }

    @Override
    public String toString() {
        StringBuilder detalle = new StringBuilder("Cliente: " + nombreCliente + "\nProductos:\n");
        for (Producto producto : productos) {
            detalle.append(" - ").append(producto).append("\n");
        }
        return detalle.toString();
    }
}

public class SistemaCafeteria {
    private Queue<Pedido> pedidosPendientes;

    public SistemaCafeteria() {
        pedidosPendientes = new LinkedList<>();
    }

    public void registrarPedido() {
        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre válido.");
            return;
        }

        Pedido nuevoPedido = new Pedido(nombreCliente);
        
        int continuar;
        do {
            String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto:"));

            nuevoPedido.agregarProducto(nombreProducto, cantidad);

            continuar = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto?", "Agregar Producto", JOptionPane.YES_NO_OPTION);
        } while (continuar == JOptionPane.YES_OPTION);

        pedidosPendientes.add(nuevoPedido);
        JOptionPane.showMessageDialog(null, "Pedido registrado para el cliente: " + nombreCliente);
    }

    public void atenderPedido() {
        if (!pedidosPendientes.isEmpty()) {
            Pedido pedidoAtendido = pedidosPendientes.poll();
            JOptionPane.showMessageDialog(null, "Atendiendo pedido:\n" + pedidoAtendido);
        } else {
            JOptionPane.showMessageDialog(null, "No hay pedidos pendientes.");
        }
    }

    public void mostrarPedidosPendientes() {
        if (!pedidosPendientes.isEmpty()) {
            StringBuilder pedidos = new StringBuilder("Pedidos pendientes:\n");
            for (Pedido pedido : pedidosPendientes) {
                pedidos.append(pedido).append("\n");
            }
            JOptionPane.showMessageDialog(null, pedidos.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay pedidos pendientes.");
        }
    }

    public static void main(String[] args) {
        SistemaCafeteria sistema = new SistemaCafeteria();
        String[] opciones = {"Registrar Pedido", "Atender Pedido", "Ver Pedidos Pendientes", "Salir"};
        
        int opcion;
        do {
            opcion = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción:",
                "Sistema de Cafetería",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            switch (opcion) {
                case 0 -> sistema.registrarPedido();
                case 1 -> sistema.atenderPedido();
                case 2 -> sistema.mostrarPedidosPendientes();
                case 3 -> JOptionPane.showMessageDialog(null, "Cerrando sistema.");
            }
        } while (opcion != 3);
    }
}
