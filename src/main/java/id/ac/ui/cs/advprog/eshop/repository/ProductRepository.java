package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductID(String.valueOf(productData.size() + 1));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean delete(String id) {
        return productData.removeIf(product -> product.getProductID().equals(id));
    }

    public Product findProductbyId(String id) {
        return productData.stream()
                .filter(product -> product.getProductID().equals(id))
                .findFirst()
                .orElse(null);
        }

    public Product edit(Product editProduct) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductID().equals(editProduct.getProductID())) {
                productData.set(i, editProduct);
                return editProduct;
            }
        }
        return null;
    }
}