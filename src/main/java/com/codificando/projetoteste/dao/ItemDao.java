package com.codificando.projetoteste.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.codificando.projetoteste.model.Item;

//@Repository
public class ItemDao {
	
	/*
	 * @PersistenceContext private EntityManager em;
	 */
	
	EntityManager em = Persistence.createEntityManagerFactory("mysqlDataSource").createEntityManager();
	
	public Item insert(Item item) {
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		em.close();
		return item;
	}
	
	public Item find(int id) {
		return em.find(Item.class, id);
	}

}
