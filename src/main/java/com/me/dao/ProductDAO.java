package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Product;
import com.me.pojo.User;

public class ProductDAO extends DAO {
	
	public ProductDAO() {
		
	}
	
	public Product register(Product p) throws Exception {
		try {
			begin();
			getSession().save(p);
			commit();
			close();
			return p;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating product: " + e.getMessage());
		}
	}

	public List<Product> getAllProducts() {
		try {
			begin();
			Query q = getSession().createQuery("from Product");
			List<Product> prodList = q.list();
			close();
			return prodList;

		} catch (HibernateException e) {
			System.out.println("Error getting Product List " + e.getMessage());
		}
		return null;
	}

	public List<Product> getUserProducts(long userId) {
		try {
		begin();
		Query q = getSession().createQuery("from Product where id=:userId");
		q.setLong("userId", userId);
		List<Product> prodList = q.list();
		close();
		return prodList;
		} catch (HibernateException e) {
			System.out.println("Error getting Product List " + e.getMessage());
		}
		return null;
	}
}
