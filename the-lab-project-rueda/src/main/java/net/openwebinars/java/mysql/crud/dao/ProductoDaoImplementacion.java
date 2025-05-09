package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public class ProductoDaoImplementacion implements ProductoDao{

    private static ProductoDaoImplementacion instance;

    static {
        instance = new ProductoDaoImplementacion();
    }

    private ProductoDaoImplementacion() {}

    public static ProductoDaoImplementacion getInstance() {
        return instance;
    }

    @Override
    public int add(Producto prod) throws SQLException {
        String sql = """
            INSERT INTO producto 
                (nombre, descripcion, precio, stock, id_categoria)
            VALUES (?, ?, ?, ?, ?)
            """;

        int result;

        try (Connection conn = MyDataSource.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, prod.getNombre());
            pstm.setString(2, prod.getDescripcion());
            pstm.setDouble(3, prod.getPrecio());
            pstm.setInt(4, prod.getStock());
            pstm.setInt(5, prod.getId_categoria());

            result = pstm.executeUpdate();
        }
        return result;
    }

    @Override
    public Producto getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Producto> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int update(Producto prod) throws SQLException {
        return 0;
    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
