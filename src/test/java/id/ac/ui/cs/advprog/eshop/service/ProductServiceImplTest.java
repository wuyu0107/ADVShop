package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductID("1");
        product.setProductName("Apple");
        product.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        Product newProduct = new Product();
        when(productRepository.create(newProduct)).thenReturn(newProduct);
        Product result = productServiceImpl.create(newProduct);

        assertEquals(newProduct, result);
        verify(productRepository).create(newProduct);
    }

    @Test
    void testFindAll() {
        List<Product> productList = Collections.singletonList(product);
        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productServiceImpl.findAll();

        assertNotNull(allProducts);
        assertEquals(1, allProducts.size());
        assertEquals("Apple", allProducts.getFirst().getProductName());
        verify(productRepository).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findProductbyId("1")).thenReturn(product);
        Product result = productServiceImpl.findProductbyId("1");

        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository).findProductbyId("1");
    }

    @Test
    void testDelete() {
        when(productRepository.delete("1")).thenReturn(true);

        boolean result = productServiceImpl.delete("1");
        assertTrue(result);
        verify(productRepository).delete("1");
    }

    @Test
    void testEdit() {
        when(productRepository.edit(product)).thenReturn(product);

        Product editedProduct = productServiceImpl.edit(product);
        assertEquals(product, editedProduct);
        verify(productRepository).edit(product);
    }

}
