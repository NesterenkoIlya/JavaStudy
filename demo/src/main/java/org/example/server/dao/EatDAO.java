package org.example.server.dao;

import java.util.Collection;
import org.example.eatshop.product.Product;

public interface ProductDAO {
    /**
     * Add a product to order
     * 
     * @param product
     * @return
     * @throws DAOException
     */
    public int addProduct(Product item) throws DAOException;

    /**
     * Remove a product from order
     * 
     * @param product
     * @return
     * @throws DAOException
     */
    public Product deleteProduct (int id) throws DAOException;


}