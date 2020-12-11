package com.elena.Warehouse1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CashRegisterTable {

	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
public int addCashRegister(CashRegister cashRegister) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				
				em.persist(cashRegister);
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
			return cashRegister.getId();
	}
	
	
	public CashRegister getCashRegister(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM CashRegister c WHERE c.id = :id";
		TypedQuery<CashRegister> tq = em.createQuery(query, CashRegister.class);
		tq.setParameter("id", id);
		CashRegister cashRegister = null;
		try {
			cashRegister = tq.getSingleResult();
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return cashRegister;
	}
	
	public CashRegister getCashRegister(String name) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM CashRegister c WHERE c.name = :name";
		TypedQuery<CashRegister> tq = em.createQuery(query, CashRegister.class);
		tq.setParameter("name", name);
		CashRegister cashRegister = null;
		try {
			cashRegister = tq.getSingleResult();
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return cashRegister;
	}
	
	
	public List<CashRegister> getAllCashRegisters() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT c FROM CashRegister c WHERE c.id IS NOT NULL";
		TypedQuery<CashRegister> tq = em.createQuery(strQuery, CashRegister.class);
		List<CashRegister> cashRegisters = null;
		try {
			cashRegisters = tq.getResultList();
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return cashRegisters;
	}
	
	
	public void updateCashRegister(int id, CashRegister cashRegister) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			cashRegister.setId(id);
			em.merge(cashRegister);
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
	
	
	public void deleteCashRegister(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		CashRegister cashRegister = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			cashRegister = em.find(CashRegister.class, id);
			em.remove(cashRegister);
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
