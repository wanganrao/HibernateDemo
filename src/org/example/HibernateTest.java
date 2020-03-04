package org.example;


import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	private static SessionFactory sessionFactory=null;
	
	public static void main(String[] args) {

		sessionFactory = new Configuration().configure().buildSessionFactory();

		UserDetails user = new UserDetails();
		user.setUserId("5");
		user.setUserName("Fifth User");
		//addUser(user);
		
		listUsers();
		

		UserDetails u = new UserDetails();
		u.setUserId("2");
		u.setUserName("Second User");
		//updateUser(u);
		
		//listUsers();

		deleteUser("3");
		
		listUsers();

	}

	
	private static void deleteUser(String userId) {
		Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         UserDetails user = (UserDetails) session.get(UserDetails.class, userId);
	         session.delete(user);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }				
	}

	private static void updateUser(UserDetails u) {
		Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         //UserDetails user = (UserDetails) session.get(UserDetails.class, u.getUserId());
	         session.update(u);
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }		
		
	}

	private static void listUsers() {
		Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM UserDetails").list(); 
	         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
	            UserDetails employee = (UserDetails) iterator.next(); 
	            System.out.println("User ID: " + employee.getUserId() + "User Name: " + employee.getUserName()); 
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }		
	}

	public static void addUser(UserDetails user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx=session.beginTransaction();
			session.save(user);
			tx.commit();
		}catch(HibernateException e) {
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
