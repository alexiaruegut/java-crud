package net.openwebinars.java.mysql.crud;

import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.model.Categoria;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import net.openwebinars.java.mysql.crud.dao.ProductoDao;
import net.openwebinars.java.mysql.crud.dao.ProductoDaoImplementacion;
import net.openwebinars.java.mysql.crud.dao.CategoriaDao;
import net.openwebinars.java.mysql.crud.dao.CategoriaDaoImplementacion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.init();
    }

    public static void pruebaDao() {
        ProductoDao productoDao = ProductoDaoImplementacion.getInstance();
        CategoriaDao categoriaDao = CategoriaDaoImplementacion.getInstance();

        Producto nuevoProducto = new Producto(
                "Collar de diamantes",
                "Collar dorado de Swaroski con diamantes",
                1099.99,
                22,
                1
        );

        Categoria nuevaCategoria = new Categoria(
                "Ropa Mujer",
                "Camisetas, vestidos, faldas, etc."
        );

        try {
            // ---------- PRODUCTO ----------

            // ADD
            int resultadoProducto = productoDao.add(nuevoProducto);
            System.out.println("Nº productos agregados: " + resultadoProducto);
            System.out.println("Info producto agregado: " + nuevoProducto);

            // GET ALL
            List<Producto> productos = productoDao.getAll();
            System.out.println("Listado de productos:");
            if (productos.isEmpty()) {
                System.out.println("No hay productos registrados");
            } else {
                productos.forEach(System.out::println);
            }

            // GET BY ID
            Producto prod1 = productoDao.getById(4);
            System.out.println("Producto por ID específico:");
            System.out.println(prod1);

            // UPDATE
            prod1.setPrecio(22);
            prod1.setStock(75);
            int updatedCount = productoDao.update(prod1);
            System.out.println("Nº productos actualizados: " + updatedCount);

            Producto actualizado = productoDao.getById(4);
            System.out.println("Info producto actualizado:");
            System.out.println(actualizado);

            // DELETE
            productoDao.delete(6);
            productos = productoDao.getAll();
            System.out.println("Productos después de eliminar:");
            if (productos.isEmpty()) {
                System.out.println("No hay productos registrados");
            } else {
                productos.forEach(System.out::println);
            }

            // ---------- CATEGORÍA ----------

            // ADD
            int resultadoCategoria = categoriaDao.add(nuevaCategoria);
            System.out.println("Nº categorías agregadas: " + resultadoCategoria);
            System.out.println("Info categoría agregada: " + nuevaCategoria);

            // GET ALL
            List<Categoria> categorias = categoriaDao.getAll();
            System.out.println("Listado de categorías:");
            if (categorias.isEmpty()) {
                System.out.println("No hay categorías registradas");
            } else {
                categorias.forEach(System.out::println);
            }

            // GET BY ID
            Categoria cat1 = categoriaDao.getById(1);
            System.out.println("Categoría por ID específico:");
            System.out.println(cat1);

            // UPDATE
            cat1.setNombre("Ropa - Mujer");
            int updatedCatCount = categoriaDao.update(cat1);
            System.out.println("Nº categorías actualizadas: " + updatedCatCount);

            Categoria catActualizada = categoriaDao.getById(1);
            System.out.println("Info categoría actualizada:");
            System.out.println(catActualizada);

            // DELETE
            categoriaDao.delete(2);
            categorias = categoriaDao.getAll();
            System.out.println("Categorías después de eliminar:");
            if (categorias.isEmpty()) {
                System.out.println("No hay categorías registradas");
            } else {
                categorias.forEach(System.out::println);
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
