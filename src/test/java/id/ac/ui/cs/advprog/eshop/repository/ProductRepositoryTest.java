package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductID(), savedProduct.getProductID());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductID("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductID(), savedProduct.getProductID());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductID(), savedProduct.getProductID());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void editExistingProduct() {
        Product product1 = new Product();
        product1.setProductName("Apple");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        Product updatedProduct = new Product();
        updatedProduct.setProductID(product1.getProductID());
        updatedProduct.setProductName("Banana");
        updatedProduct.setProductQuantity(40);
        productRepository.create(updatedProduct);

        // Should edit(update) the details of product
        Product editedProduct = productRepository.edit(updatedProduct);
        assertNotNull(editedProduct);
        assertEquals(editedProduct.getProductName(), updatedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), updatedProduct.getProductQuantity());
    }

    @Test
    void editNonExistingProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductID("does-not-exist");
        updatedProduct.setProductName("Apple");
        updatedProduct.setProductQuantity(10);

        // Should give a null when trying to edit
        Product editedProduct = productRepository.edit(updatedProduct);
        assertNull(editedProduct);
    }

    @Test
    void deleteExistingProduct() {
        Product newProduct = new Product();
        newProduct.setProductName("Apple");
        newProduct.setProductQuantity(10);
        productRepository.create(newProduct);

        // When product is deleted, should result a True
        boolean isDeleted = productRepository.delete(newProduct.getProductID());
        assertTrue(isDeleted);
    }

    @Test
    void deleteNonExistingProduct() {
        // When non-existing product is deleted, returns a False
        boolean isDeleted = productRepository.delete("does-not-exist");
        assertFalse(isDeleted);
    }

    @Test
    void findExistingProductByProductID() {
        Product product = new Product();
        product.setProductName("Apple");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product foundProduct = productRepository.findProductbyId("1");
        assertNotNull(foundProduct);
        assertEquals(foundProduct.getProductName(), product.getProductName());
        assertEquals(foundProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void findNonExistingProductByProductID() {
        Product foundProduct = productRepository.findProductbyId("does-not-exist");
        assertNull(foundProduct);
    }

    @Test
    void findProductByEmptyProductID() {
        Product foundProduct = productRepository.findProductbyId("");
        assertNull(foundProduct);
    }
}

