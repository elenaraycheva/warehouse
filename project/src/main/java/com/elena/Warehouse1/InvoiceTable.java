package com.elena.Warehouse1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class InvoiceTable {
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
public int addInvoice(Invoice invoice) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
			
				em.persist(invoice);
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
			return invoice.getId();
		}
	
	
	
	public Invoice getInvoice(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT i FROM Invoice i WHERE i.id = :id_invoice";
		TypedQuery<Invoice> tq = em.createQuery(query, Invoice.class);
		tq.setParameter("id_invoice", id);
		Invoice invoice = null;
		try {
			invoice = tq.getSingleResult();
			System.out.println(invoice.getId() + " " +invoice.getDate());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return invoice;
	}
	
	
	public List<Invoice> getAllInvoices() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT i FROM Invoice i WHERE i.id IS NOT NULL";
		TypedQuery<Invoice> tq = em.createQuery(strQuery, Invoice.class);
		List<Invoice> invoices = null;
		try {
			invoices = tq.getResultList();
			invoices.forEach(invoice->System.out.println(invoice.getId() + " " +invoice.getDate()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return invoices;
	}
	
	
	public void updateInvoice(int id, Invoice invoice) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			invoice.setId(id);
			em.merge(invoice);
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
	
	
	public void deleteInvoice(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Invoice invoice = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			invoice = em.find(Invoice.class, id);
			em.remove(invoice);
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



