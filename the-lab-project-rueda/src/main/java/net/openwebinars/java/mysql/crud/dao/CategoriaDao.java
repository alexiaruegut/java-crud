package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Categoria;
import net.openwebinars.java.mysql.crud.model.Producto;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaDao {
    public int add(Categoria cat) throws SQLException;

    public Categoria getById(int id) throws SQLException;

    public List<Categoria> getAll() throws SQLException;

    public int update(Categoria cat) throws SQLException;

    public void delete(int id) throws SQLException;
}
