package com.elena.Warehouse1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsersTable {
	private Connection con;
	private Statement stmt;
	private ResultSet result;
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
	public boolean userExists(String userName) {
		String m = "";
		boolean chk = false;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse1", "root", "");
			stmt = con.createStatement();
			String query = "SELECT user_name FROM users WHERE user_name = \"" + userName + "\"";

			result = stmt.executeQuery(query);
			
			while(result.next()) {
				m = result.getString(1);
				System.out.println(m);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		
		if(m.equalsIgnoreCase(userName)) {
			chk = true;
		}
		
		return chk;
	}
	
	
	public int addUser(Users user) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		if(!userExists(user.getName())) {
			try {
				et = em.getTransaction();
				et.begin();
				
				em.persist(user);
				em.flush();
				et.commit();
			} 
			catch(Exception ex) {
				if(et != null) {
					et.rollback();
				}
				ex.printStackTrace();
			}
			finally {
				em.close();
			}
		}else {
			
		}
		System.out.println(user.getId());
		return user.getId();
	}
	
	
	public Users getUser(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM Users u WHERE u.id = :id_user";
		TypedQuery<Users> tq = em.createQuery(query, Users.class);
		tq.setParameter("id_user", id);
		Users user = null;
		try {
			user = tq.getSingleResult();
			System.out.println(user.getName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return user;
	}
	
	public Users getUser(String name) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT u FROM Users u WHERE u.name = :user_name";
		TypedQuery<Users> tq = em.createQuery(query, Users.class);
		tq.setParameter("user_name", name);
		Users user = null;
		try {
			user = tq.getSingleResult();
			System.out.println(user.getName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return user;
	}
	
	
	public List<Users> getAllUsers() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT u FROM Users u WHERE u.id IS NOT NULL";
		TypedQuery<Users> tq = em.createQuery(strQuery, Users.class);
		List<Users> users = null;
		try {
			users = tq.getResultList();
			users.forEach(user->System.out.println(user.getName()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return users;
	}
	
	
	public void updateUser(int id, Users user) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			user.setId(id);
			em.merge(user);
			et.commit();
		} 
		catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
	}
	
	
	public void deleteUser(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Users user = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			user = em.find(Users.class, id);
			em.remove(user);
			et.commit();
		} 
		catch(Exception ex) {
			if(et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
	}
}
