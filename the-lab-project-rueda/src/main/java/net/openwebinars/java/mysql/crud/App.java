package net.openwebinars.java.mysql.crud;

import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import net.openwebinars.java.mysql.crud.dao.ProductoDao;
import net.openwebinars.java.mysql.crud.dao.ProductoDaoImplementacion;
//import net.openwebinars.java.mysql.crud.model.Empleado;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {
        pruebaDao();
    }

    public static void pruebaDao() {
        ProductoDao dao = ProductoDaoImplementacion.getInstance();

        Producto nuevoProducto = new Producto(
                "Collar de diamantes",
                "Collar dorado de Swaroski con diamantes",
                1099.99,
                22,
                1
        );

        try {
//            int resultado = dao.add(nuevoProducto);
//            System.out.println("Nº productos agregados: " + resultado);
//            System.out.println("Info producto agregado: " + nuevoProducto);

            List<Producto> productos = dao.getAll();

            if (productos.isEmpty()) {
                System.out.println("No hay productos");
            } else {
                System.out.println("Listado de productos:");
                productos.forEach(System.out::println);
            }

            Producto prod1 = dao.getById(4);
            System.out.println("Producto por id específico:");
            System.out.println(prod1);

            Producto prod2 = dao.getById(4);

            prod2.setPrecio(22);
            prod2.setStock(75);

            int n = dao.update(prod2);
            System.out.println("Nº productos actualizados: " + n);

            prod2 = dao.getById(4);
            System.out.println("Info producto actualizado: " + prod2);

            dao.delete(6);
            productos = dao.getAll();
            if (productos.isEmpty())
                System.out.println("No hay productos registrados");
            else
                productos.forEach(System.out::println);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void pruebaPool() {
        try (Connection conn = MyDataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet tables = metaData.getTables(null, null, "%", types);
            while(tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
