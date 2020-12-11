package com.elena.Warehouse1;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class OperatorsTable {

	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
	
	
	
	public int addOperator(Operators operator) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				em.persist(operator);
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
			return operator.getId();
		}
	
	
	
	public Operators getOperator(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT o FROM Operators o WHERE o.id = :id_operator";
		TypedQuery<Operators> tq = em.createQuery(query, Operators.class);
		tq.setParameter("id_operator", id);
		Operators operator = null;
		try {
			operator = tq.getSingleResult();
			System.out.println(operator.getFirstName() + " " + operator.getLastName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return operator;
	}
	
	public Operators getOperatorByUser(Users user) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT o FROM Operators o WHERE o.user = :user_id";
		TypedQuery<Operators> tq = em.createQuery(query, Operators.class);
		tq.setParameter("user_id", user);
		Operators operator = null;
		try {
			operator = tq.getSingleResult();
			System.out.println(operator.getFirstName() + " " + operator.getLastName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return operator;
	}
	
	
	public List<Operators> getAllOperators() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT o FROM Operators o WHERE o.id IS NOT NULL";
		TypedQuery<Operators> tq = em.createQuery(strQuery, Operators.class);
		List<Operators> operators = null;
		try {
			operators = tq.getResultList();
			operators.forEach(operator->System.out.println(operator.getFirstName() + " " + operator.getLastName()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return operators;
	}
	
	
	public void updateOperator(int id, Operators operator) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			operator.setId(id);
			em.merge(operator);
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
	
	
	public void deleteOperator(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Operators operator = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			operator = em.find(Operators.class, id);
			em.remove(operator);
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
