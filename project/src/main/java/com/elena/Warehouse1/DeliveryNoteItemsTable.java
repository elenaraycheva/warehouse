package com.elena.Warehouse1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DeliveryNoteItemsTable {
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
public int addDeliveryNoteItems(DeliveryNoteItems deliveryNoteItem) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
			
				em.persist(deliveryNoteItem);
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
			return deliveryNoteItem.getId();
		}
	
	
	
	public DeliveryNoteItems getDeliveryNoteItem(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT d FROM DeliveryNoteItems d WHERE d.id = :id";
		TypedQuery<DeliveryNoteItems> tq = em.createQuery(query, DeliveryNoteItems.class);
		tq.setParameter("id", id);
		DeliveryNoteItems deliveryNoteItem = null;
		try {
			deliveryNoteItem = tq.getSingleResult();
			System.out.println(deliveryNoteItem.getId() + " " +deliveryNoteItem.getStock());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return deliveryNoteItem;
	}
	
	
	public List<DeliveryNoteItems> getAllDeliveryNoteItems() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT d FROM DeliveryNoteItems d WHERE d.id IS NOT NULL";
		TypedQuery<DeliveryNoteItems> tq = em.createQuery(strQuery, DeliveryNoteItems.class);
		List<DeliveryNoteItems> deliveryNoteItems = null;
		try {
			deliveryNoteItems = tq.getResultList();
			deliveryNoteItems.forEach(dni->System.out.println(dni.getId() + " " +dni.getStock()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return deliveryNoteItems;
	}
	
	
	public void updateDeliveryNoteItem(int id, DeliveryNoteItems deliveryNoteItem) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			deliveryNoteItem.setId(id);
			em.merge(deliveryNoteItem);
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
	
	
	public void deleteDeliveryNoteItem(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		DeliveryNoteItems deliveryNoteItem = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			deliveryNoteItem = em.find(DeliveryNoteItems.class, id);
			em.remove(deliveryNoteItem);
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

