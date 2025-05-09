package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface ProductoDao {
    public int add(Producto prod) throws SQLException;

    public Producto getById(int id) throws SQLException;

    public List<Producto> getAll() throws SQLException;

    public int update(Producto prod) throws SQLException;

    public void delete(int id) throws SQLException;
}
