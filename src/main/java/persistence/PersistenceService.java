package persistence;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersistenceService {
	private static Context ctx;
	private static EntityManagerFactory emf;

	private PersistenceService() {
		// Intentionally left empty.
	}

	static {
		try {
			ctx = new InitialContext();
			emf = (EntityManagerFactory) ctx.lookup("java:comp/env/jdbc/emf");
		} catch (NamingException e) {
			System.out.println("Failed to create entity manager factory.");
			e.printStackTrace();
		}
	}

	public static <T> List<T> search(String namedQueryName, Class<T> resultClass) {
		EntityManager em = emf.createEntityManager();
		List<T> results = em.createNamedQuery(namedQueryName, resultClass).getResultList();
		em.close();
		return results;
	}

	public static boolean persist(Object o) {
		boolean success = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to persist data.");
			e.printStackTrace();
		}
		return success;
	}

	public static <T> T find(Class<T> entityClass, Object primaryKey) {
		EntityManager em = emf.createEntityManager();
		T object = em.find(entityClass, primaryKey);
		em.close();
		return object;
	}

	public static boolean remove(Class<?> entityClass, Object primaryKey) {
		boolean success = false;
		try {
			Object object = find(entityClass, primaryKey);
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			if (!em.contains(object)) {
				em.remove(em.merge(object));
			}
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to remove data.");
			e.printStackTrace();
		}
		return success;
	}

	public static boolean remove(Object o) {
		boolean success = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			if (!em.contains(o)) {
				em.remove(em.merge(o));
			}
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to remove data.");
			e.printStackTrace();
		}
		return success;
	}
}
