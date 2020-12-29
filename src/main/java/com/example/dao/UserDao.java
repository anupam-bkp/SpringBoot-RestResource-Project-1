package com.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.models.User;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	
	public void persistUser(final User user) {
		em.persist(user);
	}
	
	public List<User> fetchAllUsers() {
		final TypedQuery<User> query = 
				em.createQuery("select u from User u", User.class);
		
		return query.getResultList();
	}
	
	
	public User getUserByUsername(final String username) {
		
		final TypedQuery<User> query = em.createQuery(
				"select u from User u where u.username = ?1", User.class)
			.setParameter(1, username);
		
		return query.getSingleResult();
	}
}
