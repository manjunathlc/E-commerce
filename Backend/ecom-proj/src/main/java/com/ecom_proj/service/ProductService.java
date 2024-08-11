package com.ecom_proj.service;

import com.ecom_proj.model.Product;
import com.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();

    }

    public Product getProductById(Integer id) {
        Optional<Product> optional=repo.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }

        return null;
        // throws NoSuchElementException if not found
//       return repo.findById(id).orElse(null); // returns Optional<Product>
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes()); // save the image data in the database
        return repo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        Product existingProduct = repo.findById(id).orElse(null);
//        if (existingProduct == null) {
//            return null;
//        }
//
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setStockQuantity(product.getStockQuantity());
//
//        if (imageFile!= null) {
//            existingProduct.setImageName(imageFile.getOriginalFilename());
//            existingProduct.setImageType(imageFile.getContentType());
//            existingProduct.setImageData(imageFile.getBytes()); // save the image data in the database
//        }
//
//        return repo.save(existingProduct);

        product.setImageData(imageFile.getBytes()); // save the image data in the database
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
