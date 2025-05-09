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

//        Producto nuevoProducto = new Producto(
//                "Pulsera con charms",
//                "Pulsera Pandora con Charms dorados y plateados.",
//                199.99,
//                22,
//                1
//        );

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
