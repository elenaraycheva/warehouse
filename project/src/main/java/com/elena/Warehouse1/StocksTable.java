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

public class StocksTable {
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
	
	
	public int addStock(Stocks stock) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
			
				em.persist(stock);
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
			
			return stock.getId();
		}
	
	
	
	public Stocks getStock(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT s FROM Stocks s WHERE s.id = :id_stock";
		TypedQuery<Stocks> tq = em.createQuery(query, Stocks.class);
		tq.setParameter("id_stock", id);
		Stocks stock = null;
		try {
			stock = tq.getSingleResult();
			System.out.println(stock.getName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return stock;
	}
	
	public Stocks getStock(String name, Brands brand) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT s FROM Stocks s WHERE s.name = :name AND s.brand = :brand_id";
		TypedQuery<Stocks> tq = em.createQuery(query, Stocks.class);
		tq.setParameter("name", name);
		tq.setParameter("brand_id", brand);
		Stocks stock = null;
		try {
			stock = tq.getSingleResult();
			System.out.println(stock.getName());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return stock;
	}
	
	
	public List<Stocks> getAllStocks() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT s FROM Stocks s WHERE s.id IS NOT NULL";
		TypedQuery<Stocks> tq = em.createQuery(strQuery, Stocks.class);
		List<Stocks> stocks = null;
		try {
			stocks = tq.getResultList();
			stocks.forEach(stock->System.out.println(stock.getName()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return stocks;
	}
	
	
	public void updateStock(int id, Stocks stock) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			stock.setId(id);
			em.merge(stock);
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
	
	
	public void deleteStock(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Stocks stock = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			stock = em.find(Stocks.class, id);
			em.remove(stock);
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


