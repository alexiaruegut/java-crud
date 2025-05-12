package net.openwebinars.java.mysql.crud;

import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.model.Categoria;

import net.openwebinars.java.mysql.crud.dao.ProductoDao;
import net.openwebinars.java.mysql.crud.dao.ProductoDaoImplementacion;

import net.openwebinars.java.mysql.crud.dao.CategoriaDao;
import net.openwebinars.java.mysql.crud.dao.CategoriaDaoImplementacion;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

public class Menu {

    private KeyboardReader reader;
    private ProductoDao productoDao;
    private CategoriaDao categoriaDao;

    public Menu() {
        reader = new KeyboardReader();
        productoDao = ProductoDaoImplementacion.getInstance();
        categoriaDao = CategoriaDaoImplementacion.getInstance();
    }

    public void init() {
        int option;

        do {
            showMenu();
            option = reader.nextInt();

            switch (option) {
                case 1 -> listAllProducts();
                case 2 -> getProductById();
                case 3 -> addProduct();
                case 4 -> updateProduct();
                case 5 -> deleteProduct();
                case 6 -> listAllCategories();
                case 7 -> getCategoryById();
                case 8 -> addCategory();
                case 9 -> updateCategory();
                case 10 -> deleteCategory();
                case 0 -> System.out.println("\nGoodbye!\n");
                default -> System.err.println("\nInvalid option, try again\n");
            }

        } while (option != 0);
    }

    public void showMenu() {
        System.out.println("\nCRUD M3 - ALEXIA RUEDA 2DAW");
        System.out.println("============================");
        System.out.println("..........PRODUCTS..........");
        System.out.println("1: List all products");
        System.out.println("2: Get product by ID");
        System.out.println("3: Add a new product");
        System.out.println("4: Update a product");
        System.out.println("5: Delete a product");
        System.out.println(".........CATEGORIES.........");
        System.out.println("6: List all categories");
        System.out.println("7: Get category by ID");
        System.out.println("8: Add a new category");
        System.out.println("9: Update a category");
        System.out.println("10: Delete a category");
        System.out.println("");
        System.out.println("0: Exit");
        System.out.print("\nSelect an option: ");
    }

    private void listAllProducts() {
        try {
            List<Producto> products = productoDao.getAll();
            if (products.isEmpty()) {
                System.out.println("No products found.");
            } else {
                products.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getProductById() {
        System.out.print("Enter product ID: ");
        int id = reader.nextInt();
        try {
            Producto prod = productoDao.getById(id);
            System.out.println(prod != null ? prod : "Product not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addProduct() {
        System.out.print("Product name: ");
        String name = reader.nextLine();
        System.out.print("Description: ");
        String desc = reader.nextLine();
        System.out.print("Price: ");
        double price = reader.nextDouble();
        System.out.print("Stock: ");
        int stock = reader.nextInt();
        System.out.print("Category ID: ");
        int categoryId = reader.nextInt();

        Producto p = new Producto(name, desc, price, stock, categoryId);
        try {
            int r = productoDao.add(p);
            System.out.println("Rows affected: " + r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int id = reader.nextInt();
        try {
            Producto prod = productoDao.getById(id);
            if (prod == null) {
                System.out.println("Product not found.");
                return;
            }
            System.out.printf("New name (%s): ", prod.getNombre());
            String name = reader.nextLine();
            if (!name.isBlank()) prod.setNombre(name);

            System.out.printf("New description (%s): ", prod.getDescripcion());
            String desc = reader.nextLine();
            if (!desc.isBlank()) prod.setDescripcion(desc);

            System.out.printf("New price (%.2f): ", prod.getPrecio());
            String priceStr = reader.nextLine();
            if (!priceStr.isBlank()) prod.setPrecio(Double.parseDouble(priceStr));

            System.out.printf("New stock (%d): ", prod.getStock());
            String stockStr = reader.nextLine();
            if (!stockStr.isBlank()) prod.setStock(Integer.parseInt(stockStr));

            System.out.printf("New category ID (%d): ", prod.getId_categoria());
            String catIdStr = reader.nextLine();
            if (!catIdStr.isBlank()) prod.setId_categoria(Integer.parseInt(catIdStr));

            int r = productoDao.update(prod);
            System.out.println("Rows affected: " + r);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = reader.nextInt();
        try {
            productoDao.delete(id);
            System.out.println("Product deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listAllCategories() {
        try {
            List<Categoria> categories = categoriaDao.getAll();
            if (categories.isEmpty()) {
                System.out.println("No categories found.");
            } else {
                categories.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getCategoryById() {
        System.out.print("Enter category ID: ");
        int id = reader.nextInt();
        try {
            Categoria cat = categoriaDao.getById(id);
            System.out.println(cat != null ? cat : "Category not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCategory() {
        System.out.print("Category name: ");
        String name = reader.nextLine();
        System.out.print("Description: ");
        String desc = reader.nextLine();
        Categoria cat = new Categoria(name, desc);
        try {
            int r = categoriaDao.add(cat);
            System.out.println("Rows affected: " + r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCategory() {
        System.out.print("Enter category ID to update: ");
        int id = reader.nextInt();
        try {
            Categoria cat = categoriaDao.getById(id);
            if (cat == null) {
                System.out.println("Category not found.");
                return;
            }
            System.out.printf("New name (%s): ", cat.getNombre());
            String name = reader.nextLine();
            if (!name.isBlank()) cat.setNombre(name);

            System.out.printf("New description (%s): ", cat.getDescripcion());
            String desc = reader.nextLine();
            if (!desc.isBlank()) cat.setDescripcion(desc);

            int r = categoriaDao.update(cat);
            System.out.println("Rows affected: " + r);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCategory() {
        System.out.print("Enter category ID to delete: ");
        int id = reader.nextInt();
        try {
            categoriaDao.delete(id);
            System.out.println("Category deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class KeyboardReader {
        BufferedReader br;
        StringTokenizer st;

        public KeyboardReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st != null && st.hasMoreTokens())
                    str = st.nextToken("\n");
                else
                    str = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return str;
        }
    }
}
