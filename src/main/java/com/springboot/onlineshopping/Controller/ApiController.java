package com.springboot.onlineshopping.Controller;

import java.util.*;
import org.springframework.web.bind.annotation.*;
import com.springboot.onlineshopping.JDBCApplication;
import com.springboot.onlineshopping.dao.ProductDetails;

@RestController
@RequestMapping("/products")
public class ApiController {

	public JDBCApplication jdbc = new JDBCApplication();

	// http://localhost:8080/products
	@PostMapping()
	public void insertProduct(@RequestBody ProductDetails product) throws Exception {
		try {
			jdbc.InsertProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@PutMapping()
	public void UpdateProduct(@RequestBody ProductDetails product) throws Exception {
		try {
			jdbc.UpdateProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@GetMapping("/allProducts")
	public List<ProductDetails> getAllProducts() throws Exception {
		List<ProductDetails> prodDetails = new ArrayList<>();
		try {
			prodDetails.addAll(jdbc.GetAllProducts());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return prodDetails;
	}
	
	@DeleteMapping("/{prodId}")
	public void deleteProduct(@PathVariable("prodId") int id) throws Exception {
		try {
			jdbc.DeleteProduct(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@GetMapping("/{prodId}")
	public List<ProductDetails> getProduct(@PathVariable("prodId") int id) throws Exception {
		List<ProductDetails> prodDetails = new ArrayList<>();
		try {
			prodDetails.addAll(jdbc.GetProduct(id));
		} catch (Exception e) {
			System.out.println("not able to fetch product...");
			e.printStackTrace();
			throw e;
		}
		return prodDetails;
	}
}
