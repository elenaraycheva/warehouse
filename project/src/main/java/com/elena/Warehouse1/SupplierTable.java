package com.elena.Warehouse1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class SupplierTable {
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
public int addSupplier(Supplier supplier) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				
				em.persist(supplier);
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
			return supplier.getId();
	}
	
	
	public Supplier getSupplier(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT s FROM Supplier s WHERE s.id = :id_supplier";
		TypedQuery<Supplier> tq = em.createQuery(query, Supplier.class);
		tq.setParameter("id_supplier", id);
		Supplier supplier = null;
		try {
			supplier = tq.getSingleResult();
			System.out.println(supplier.getFirstName() + " " + supplier.getLastName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return supplier;
	}
	
	
	public Supplier getSupplier(String phoneNumber) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT s FROM Supplier s WHERE s.phoneNumber = :phone_number";
		TypedQuery<Supplier> tq = em.createQuery(query, Supplier.class);
		tq.setParameter("phone_number", phoneNumber);
		Supplier supplier = null;
		try {
			supplier = tq.getSingleResult();
			System.out.println(supplier.getFirstName() + " " + supplier.getLastName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return supplier;
	}
	
	
	public List<Supplier> getAllSuppliers() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT s FROM Supplier s WHERE s.id IS NOT NULL";
		TypedQuery<Supplier> tq = em.createQuery(strQuery, Supplier.class);
		List<Supplier> suppliers = null;
		try {
			suppliers = tq.getResultList();
			suppliers.forEach(supplier->System.out.println(supplier.getFirstName() + " " + supplier.getLastName()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return suppliers;
	}
	
	
	public void updateSupplier(int id, Supplier supplier) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			supplier.setId(id);
			em.merge(supplier);
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
	
	
	public void deleteSupplier(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Supplier supplier = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			supplier = em.find(Supplier.class, id);
			em.remove(supplier);
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



