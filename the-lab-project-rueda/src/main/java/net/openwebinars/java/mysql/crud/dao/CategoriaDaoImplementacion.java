package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaDaoImplementacion implements CategoriaDao{

    private static CategoriaDaoImplementacion instance;

    static {
        instance = new CategoriaDaoImplementacion();
    }

    private CategoriaDaoImplementacion() {}

    public static CategoriaDaoImplementacion getInstance() {
        return instance;
    }

    @Override
    public int add(Categoria cat) throws SQLException {
        return 0;
    }

    @Override
    public Categoria getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Categoria> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public int update(Categoria cat) throws SQLException {
        return 0;
    }

    @Override
    public void delete(int id) throws SQLException {

    }
}
