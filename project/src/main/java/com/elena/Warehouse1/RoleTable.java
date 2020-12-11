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

public class RoleTable {
	
	private Connection con;
	private Statement stmt;
	private ResultSet result;
	private  EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	



	public boolean roleExists(String role) {
		String m = "";
		boolean chk = false;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse1", "root", "");
			stmt = con.createStatement();
			String query = "SELECT role FROM role WHERE brand = \"" + role + "\"";

			result = stmt.executeQuery(query);
			
			while(result.next()) {
				m = result.getString(1);
				System.out.println(m);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		
		if(m.equalsIgnoreCase(role)) {
			chk = true;
		}
		
		return chk;
	}
	
	
	public void addRole(String role) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		if(!roleExists(role)) {
			try {
				et = em.getTransaction();
				et.begin();
				Role roleOb = new Role();
				roleOb.setRole(role);
				em.persist(roleOb);
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
	}
	
	
	public Role getRole(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT r FROM Role r WHERE r.id = :id_role";
		TypedQuery<Role> tq = em.createQuery(query, Role.class);
		tq.setParameter("id_role", id);
		Role role = null;
		try {
			role = tq.getSingleResult();
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return role;
	}
	
	
	public List<Role> getAllRoles() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT r FROM Role r WHERE r.id IS NOT NULL";
		TypedQuery<Role> tq = em.createQuery(strQuery, Role.class);
		List<Role> roles = null;
		try {
			roles = tq.getResultList();
			roles.forEach(role->System.out.println(role.getRole()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return roles;
	}
	
	
	public void updateRole(int id, Role role) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			role.setId(id);
			em.merge(role);
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
	
	
	public void deleteRole(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Role role = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			role = em.find(Role.class, id);
			em.remove(role);
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
