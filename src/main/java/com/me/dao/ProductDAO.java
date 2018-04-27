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
			Query q = getSession().createQuery("from Product where prodStatus=:prodStatus");
			q.setString("prodStatus", "created");
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

	public int getCheckedProducts() {
		try {
			begin();
			Query q = getSession().createQuery("select count(*) from Product where prodStatus=:prodStatus");
			q.setString("prodStatus", "checked");
			Long count = (Long) q.uniqueResult();
			close();
			return Long.valueOf(count).intValue();
		} catch (HibernateException e) {
			System.out.println("Error getting Product List " + e.getMessage());
		}
		return 0;
	}

	public List<Product> findProductList() {
		try {
			begin();
			Query q = getSession().createQuery("from Product");
			List<Product> pList = q.list();
			close();
			return pList;
		} catch (HibernateException e) {
			rollback();
			System.out.println("Error updating Product List " + e.getMessage());
		}
		return null;
	}

	public List<User> getAllUsers() {
		try {
			begin();
			Query q = getSession().createQuery("select distinct user from Product where prodStatus=:prodStatus");
			q.setString("prodStatus", "confirmed");
			List<User> pList = q.list();
			close();
			return pList;
		} catch (HibernateException e) {
			rollback();
			System.out.println("Error updating Product List " + e.getMessage());
		}
		return null;
	}

	public List<Product> getProducts(String email) {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail=:userEmail");
			q.setString("userEmail", email);
			User u = (User) q.uniqueResult();
			close();
			begin();
			Query q1 = getSession().createQuery("from Product where user=:user");
			q1.setParameter("user", u);
			List<Product> pList = q1.list();
			return pList;
		} catch (HibernateException e) {
			rollback();
			System.out.println("Error updating Product List " + e.getMessage());
		}
		return null;
	}

	public void updateStatus(Product p) {
		try {
			begin();
			p.setProdStatus("shipped");
			getSession().update(p);
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			System.out.println("Error updating Product List " + e.getMessage());
		}

	}
}
