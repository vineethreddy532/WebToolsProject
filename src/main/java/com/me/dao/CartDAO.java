package com.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.Product;
import com.me.pojo.User;

public class CartDAO extends DAO {

	public void updateCheckedProduct(String name, String price, String location, User u) {
		try {
			begin();
			Query q = getSession().createQuery("from Product where productName = :productName and price=:price and prodLocation=:prodLocation");
			q.setString("productName", name);
			q.setString("price", price);
			q.setString("prodLocation", location);
			Product p = (Product) q.uniqueResult();
			if(p!=null){
				p.setProdStatus("checked");
				p.setUser(u);
				getSession().update(p);
				commit();
				close();
			}
		} catch (HibernateException e) {
			rollback();
			System.out.println("Error updating Product List " + e.getMessage());
		}
	}

	public List<Product> findCheckedList(User u) {
		try {
			begin();
			Query q = getSession().createQuery("from Product where prodStatus=:prodStatus and user=:user");
			q.setString("prodStatus", "checked");
			q.setParameter("user", u);
			List<Product> pList =q.list();
			close();
			return pList;
		} catch (HibernateException e) {
			rollback();
			System.out.println("Error updating Product List " + e.getMessage());
		}
		return null;
	}
}
