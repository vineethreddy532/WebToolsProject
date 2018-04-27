package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String userEmail, String password) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail and password = :password");
			q.setString("useremail", userEmail);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userEmail, e);
		}
	}

	public User get(String userEmail) {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", userEmail);
			User user = (User) q.uniqueResult();
			close();
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			rollback();
		}
		return null;

	}

	public User register(User u) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(u);
			commit();
			close();
			return u;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public boolean updateUser(String email) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", email);
			User user = (User) q.uniqueResult();
			if (user != null) {
				user.setStatus(1);
				getSession().update(user);
				commit();
				close();
				return true;
			} else {
				return false;
			}

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}

	}

	public User checkInitialUser(String email) {
		try {
			begin();
			Query q = getSession().createQuery("from User where userEmail = :useremail");
			q.setString("useremail", email);
			User user = (User) q.uniqueResult();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			System.out.println("Could not get user " + email + e.getMessage());
		}
		return null;
	}

}