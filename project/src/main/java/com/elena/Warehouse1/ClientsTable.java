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

public class ClientsTable {

	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
	public int addClient(Clients client) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				
				em.persist(client);
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
			return client.getId();
	}
	
	
	public Clients getClient(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Clients c WHERE c.id = :id_clients";
		TypedQuery<Clients> tq = em.createQuery(query, Clients.class);
		tq.setParameter("id_clients", id);
		Clients client = null;
		try {
			client = tq.getSingleResult();
			System.out.println(client.getFirstName() + " " + client.getLastName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return client;
	}
	
	public Clients getClient(String phoneNumber) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Clients c WHERE c.phoneNumber = :phone_number";
		TypedQuery<Clients> tq = em.createQuery(query, Clients.class);
		tq.setParameter("phone_number", phoneNumber);
		Clients client = null;
		try {
			client = tq.getSingleResult();
			System.out.println(client.getFirstName() + " " + client.getLastName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return client;
	}
	
	
	public List<Clients> getAllClients() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT c FROM Clients c WHERE c.id IS NOT NULL";
		TypedQuery<Clients> tq = em.createQuery(strQuery, Clients.class);
		List<Clients> clients = null;
		try {
			clients = tq.getResultList();
			clients.forEach(client->System.out.println(client.getFirstName() + " " + client.getLastName()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return clients;
	}
	
	
	public void updateClient(int id, Clients client) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			client.setId(id);
			em.merge(client);
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
	
	
	public void deleteClient(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Clients client = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			client = em.find(Clients.class, id);
			em.remove(client);
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


