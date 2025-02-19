package id.ac.ui.cs.advprog.eshop.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import org.springframework.ui.Model;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private Model model;

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController productController;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductID("1");
        product.setProductName("Apple");
        product.setProductQuantity(10);

        model = mock(Model.class);
    }

    @Test
    void testCreateProducePage() {
        String newProduct = productController.createProductPage(model);
        assertEquals("CreateProduct", newProduct);

        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProduct() {
        String newProduct = productController.createProductPost(product, model);
        assertEquals("redirect:list", newProduct);
        verify(service).create(product);
    }

    @Test
    void testListProduct() {
        List<Product> products = Collections.singletonList(product);
        when(service.findAll()).thenReturn(products);

        String view = productController.listProduct(model);
        assertEquals("ProductList", view);
        verify(model).addAttribute(eq("products"), eq(products));
    }

    @Test
    void testDeleteProduct() {
        String productID = "1";

        String view = productController.deleteProduct(productID);
        assertEquals("redirect:/product/list", view);
        verify(service).delete(productID);
    }

    @Test
    void testEditProductPage() {
        when(service.findProductbyId("1")).thenReturn(product);
        String result = productController.editProductPage("1", model);

        assertEquals("EditProduct", result);
        verify(model).addAttribute("product", product);
        verify(service).findProductbyId("1");
    }

    @Test
    void testEditProduct() {
        String result = productController.editProductPost(product, model);
        assertEquals("redirect:list", result);
        verify(service).edit(product);
    }



}
