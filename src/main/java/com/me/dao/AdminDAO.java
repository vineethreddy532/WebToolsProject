package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Product;

public class AdminDAO extends DAO{
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
}
