package com.springboot.onlineshopping;

import java.sql.*;
import java.util.*;

import com.springboot.onlineshopping.dao.ProductDetails;

public class JDBCApplication {

	String connectionString = "jdbc:mysql://localhost:3306/onlineshopping?useSSL=false";
	String username = "root";
	String password = "root";

	public List<ProductDetails> ExecuteQuery(String query) throws Exception {
		// get - get all or specific product
		List<ProductDetails> prodDetails = new ArrayList<>();

		try {
			Connection con = DriverManager.getConnection(connectionString, username, password);
			System.out.println("Connection established successfully...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				prodDetails.add(new ProductDetails(rs.getInt("id"), rs.getString("name"), rs.getInt("price")));
			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return prodDetails;
	}

	public void ExecuteUpdate(String query) throws Exception {
		// update, insert & delete --> execute update and make sure there is no
		// exception
		try {
			Connection con = DriverManager.getConnection(connectionString, username, password);
			System.out.println("Connection established successfully...");
			Statement st = con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void InsertProduct(ProductDetails product) throws Exception {
		int prodId = product.getId();
		String prodName = product.getName();
		int prodPrice = product.getPrice();

		String insertQuery = ("insert into onlineshopping " + "values ( " + prodId + " , '" + prodName + "' , "
				+ prodPrice + " )");
		try {
			ExecuteUpdate(insertQuery);
			System.out.println("Sucessfully Product Details Inserted... ");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to insert...");
			throw ex;
		}
	}

	public void DeleteProduct(int prodId) throws Exception {
		String query = "Delete from onlineshopping where id = " + prodId;

		try {
			ExecuteUpdate(query);
			System.out.println("Sucessfully Product has been deleted... ");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to delete...");
			throw ex;
		}
	}

	public void UpdateProduct(ProductDetails prodDetails) throws Exception {

		int prodId = prodDetails.getId();
		String prodName = prodDetails.getName();
		int prodPrice = prodDetails.getPrice();

		String query = "update onlineshopping set name = '" + prodName + "' , price = " + prodPrice + " where id = "
				+ prodId;

		try {
			ExecuteUpdate(query);
			System.out.println("Sucessfully Product has been updated... ");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to update...");
			throw ex;
		}
	}

	public List<ProductDetails> GetProduct(int id) throws Exception {

		List<ProductDetails> productList = new ArrayList<>();
		String query = ("select * from onlineshopping where id = " + id);

		try {
			productList.addAll(ExecuteQuery(query));
			System.out.println("Sucessfully Product has been retrived... ");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to get product...");
			throw ex;
		}

		return productList;
	}

	public List<ProductDetails> GetAllProducts() throws Exception {

		List<ProductDetails> productList = new ArrayList<>();
		String selectQuery = ("select * from onlineshopping");

		try {
			productList.addAll(ExecuteQuery(selectQuery));

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to get all products list...");
			throw ex;
		}

		return productList;
	}

}
