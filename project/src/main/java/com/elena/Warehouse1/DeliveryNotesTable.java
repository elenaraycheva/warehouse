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

public class DeliveryNotesTable {

	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
public int addDeliveryNote(DeliveryNotes deliveryNote) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
			
				em.persist(deliveryNote);
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
			return deliveryNote.getId();
		}
	
	
	
	public DeliveryNotes getDeliveryNote(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT d FROM DeliveryNotes d WHERE d.id = :id_delivery";
		TypedQuery<DeliveryNotes> tq = em.createQuery(query, DeliveryNotes.class);
		tq.setParameter("id_delivery", id);
		DeliveryNotes deliveryNote = null;
		try {
			deliveryNote = tq.getSingleResult();
			System.out.println(deliveryNote.getId() + " " +deliveryNote.getDate());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return deliveryNote;
	}
	
	
	public List<DeliveryNotes> getAllDeliveryNotes() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT d FROM DeliveryNotes d WHERE d.id IS NOT NULL";
		TypedQuery<DeliveryNotes> tq = em.createQuery(strQuery, DeliveryNotes.class);
		List<DeliveryNotes> deliveryNotes = null;
		try {
			deliveryNotes = tq.getResultList();
			deliveryNotes.forEach(dn->System.out.println(dn.getId() + " " +dn.getDate()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return deliveryNotes;
	}
	
	
	public void updateDeliveryNotes(int id, DeliveryNotes deliveryNote) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			deliveryNote.setId(id);
			em.merge(deliveryNote);
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
	
	
	public void deleteDeliveryNote(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		DeliveryNotes deliveryNote = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			deliveryNote = em.find(DeliveryNotes.class, id);
			em.remove(deliveryNote);
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

