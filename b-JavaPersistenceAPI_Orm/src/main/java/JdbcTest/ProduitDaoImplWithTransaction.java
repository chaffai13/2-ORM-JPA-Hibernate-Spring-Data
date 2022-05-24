package JdbcTest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class ProduitDaoImplWithTransaction {

	// Création de l'objet EntityManager Factory qui lit le fichier persistence.xml
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("produitsDB");
	// Création de l'objet EntityManager
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	public void saveProduct(Produit p) {

		// Création d'une transaction
		EntityTransaction transaction = entityManager.getTransaction();
		// Démarrer la transaction
		transaction.begin();

		try {
			// Enregistrer le produit p dans la BDD
			entityManager.persist(p);

			// Valider la transaction si tout se passe bien
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	// Consulter tout les produits
	public List<Produit> findAll() {
		Query query = entityManager.createQuery("select p from Produit p");
		return query.getResultList();
	}

	// Consulter tout les produits par mot clé
	public List<Produit> findByDesignation(String mc) {
		Query query = entityManager.createQuery("select p from Produit p where p.designation like :x");
		query.setParameter("x", "%" + mc + "%");
		return query.getResultList();
	}

	// Consulter un produit
	public Produit findById(int idProduit) {
		Produit p = entityManager.find(Produit.class, idProduit);
		return p;
	}

	// Mettre àjour un produit
	public void updateProduct(Produit p) {

		// Création d'une transaction
		EntityTransaction transaction = entityManager.getTransaction();
		// Démarrer la transaction
		transaction.begin();

		try {
			// Mettre àjour le produit p dans la BDD
			entityManager.merge(p);

			// Valider la transaction si tout se passe bien
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

	}

	// Supprimer un produit
	@Transactional
	public void deleteById(int idProduit) {		

		// Création d'une transaction
		EntityTransaction transaction = entityManager.getTransaction();
		// Démarrer la transaction
		transaction.begin();

		try {
			// Supprimer le produit p de la BDD
			Produit p = entityManager.find(Produit.class, idProduit);
			entityManager.remove(p);

			// Valider la transaction si tout se passe bien
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

	}
}
