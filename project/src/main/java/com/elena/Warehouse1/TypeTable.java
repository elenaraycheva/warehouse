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

public class TypeTable {
	private Connection con;
	private Statement stmt;
	private ResultSet result;
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
	public boolean typeExists(String type) {
		String m = "";
		boolean chk = false;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse1", "root", "");
			stmt = con.createStatement();
			String query = "SELECT type FROM type WHERE type = \"" + type + "\"";

			result = stmt.executeQuery(query);
			
			while(result.next()) {
				m = result.getString(1);
				System.out.println(m);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		
		if(m.equalsIgnoreCase(type)) {
			chk = true;
		}
		
		return chk;
	}
	
	public int addType(Type type) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

			try {
				et = em.getTransaction();
				et.begin();

				em.persist(type);
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
			System.out.println(type.getId());
			return type.getId();
		}	
	
	public Type getType (int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT t FROM Type t WHERE t.id = :id_type";
		TypedQuery<Type> tq = em.createQuery(query, Type.class);
		tq.setParameter("id_type", id);
		Type type = null;
		try {
			type = tq.getSingleResult();
			System.out.println(type.getType());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return type;
	}
	
	
	public Type getType (String type) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT t FROM Type t WHERE t.type = :Type";
		TypedQuery<Type> tq = em.createQuery(query, Type.class);
		tq.setParameter("Type", type);
		Type typeOb = null;
		try {
			typeOb = tq.getSingleResult();
			System.out.println(typeOb.getType());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return typeOb;
	}
	
	
	public List<Type> getAllTypes() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT t FROM Type t WHERE t.id IS NOT NULL";
		TypedQuery<Type> tq = em.createQuery(strQuery, Type.class);
		List<Type> types = null;
		try {
			types = tq.getResultList();
			types.forEach(type->System.out.println(type.getType()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return types;
	}
	
	
	public void updateType(int id, Type type) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			type.setId(id);
			em.merge(type);
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
	
	
	public void deleteType(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Type type = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			type = em.find(Type.class, id);
			em.remove(type);
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
