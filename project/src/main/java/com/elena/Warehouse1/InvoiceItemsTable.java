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

public class InvoiceItemsTable {
	
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
public int addInvoiceItems(InvoiceItems invoiceItem) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
			
				em.persist(invoiceItem);
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
			return invoiceItem.getId();
		}
	
	
	
	public InvoiceItems getInvoiceItem(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT i FROM InvoiceItems i WHERE i.id = :id";
		TypedQuery<InvoiceItems> tq = em.createQuery(query, InvoiceItems.class);
		tq.setParameter("id", id);
		InvoiceItems invoiceItem = null;
		try {
			invoiceItem = tq.getSingleResult();
			System.out.println(invoiceItem.getId() + " " +invoiceItem.getStock());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return invoiceItem;
	}
	
	
	public List<InvoiceItems> getAllInvoiceItems() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT i FROM InvoiceItems i WHERE i.id IS NOT NULL";
		TypedQuery<InvoiceItems> tq = em.createQuery(strQuery, InvoiceItems.class);
		List<InvoiceItems> invoiceItems = null;
		try {
			invoiceItems = tq.getResultList();
			invoiceItems.forEach(invoiceItem->System.out.println(invoiceItem.getId() + " " +invoiceItem.getStock()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return invoiceItems;
	}
	
	
	public void updateInvoiceItem(int id, InvoiceItems invoiceItem) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			invoiceItem.setId(id);
			em.merge(invoiceItem);
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
	
	
	public void deleteInvoiceItem(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		InvoiceItems invoiceItem = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			invoiceItem = em.find(InvoiceItems.class, id);
			em.remove(invoiceItem);
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

