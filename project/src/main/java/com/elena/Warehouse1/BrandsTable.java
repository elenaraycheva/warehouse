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

public class BrandsTable {

	private Connection con;
	private Statement stmt;
	private ResultSet result;
	private EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Warehouse1");
	
	public boolean brandExists(String brandName) {
		String m = "";
		boolean chk = false;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse1", "root", "");
			stmt = con.createStatement();
			String query = "SELECT brand FROM brands WHERE brand = \"" + brandName + "\"";

			result = stmt.executeQuery(query);
			
			while(result.next()) {
				m = result.getString(1);
				System.out.println(m);
			}
		}
		catch(Exception e) {
			System.out.println(e);
			
		}
		
		if(m.equalsIgnoreCase(brandName)) {
			chk = true;
		}
		
		return chk;
	}
	
	
	public int addBrand(Brands brand) {	
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
			try {
				et = em.getTransaction();
				et.begin();
				em.persist(brand);
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
			System.out.println(brand.getId());
			return brand.getId();
	}
	
	
	public Brands getBrand(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT b FROM Brands b WHERE b.id = :id_brand";
		TypedQuery<Brands> tq = em.createQuery(query, Brands.class);
		tq.setParameter("id_brand", id);
		Brands brand = null;
		try {
			brand = tq.getSingleResult();
			System.out.println(brand.getBrand());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return brand;
	}
	
	
	public Brands getBrand(String brand) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT b FROM Brands b WHERE b.brand = :brand";
		TypedQuery<Brands> tq = em.createQuery(query, Brands.class);
		tq.setParameter("brand", brand);
		Brands brandOb = null;
		try {
			brandOb = tq.getSingleResult();
			System.out.println(brandOb.getBrand());
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return brandOb;
	}
	
	
	public List<Brands> getAllBrands() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT b FROM Brands b WHERE b.id IS NOT NULL";
		TypedQuery<Brands> tq = em.createQuery(strQuery, Brands.class);
		List<Brands> brands = null;
		try {
			brands = tq.getResultList();
			brands.forEach(brand->System.out.println(brand.getBrand()));
			}
		catch (NoResultException ex) {
			ex.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return brands;
	}
	
	
	public void updateBrand(int id, Brands brand) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		
		
		try {
			et = em.getTransaction();
			et.begin();
			brand.setId(id);
			em.merge(brand);
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
	
	
	public void deleteBrand(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Brands brand = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			brand = em.find(Brands.class, id);
			em.remove(brand);
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
