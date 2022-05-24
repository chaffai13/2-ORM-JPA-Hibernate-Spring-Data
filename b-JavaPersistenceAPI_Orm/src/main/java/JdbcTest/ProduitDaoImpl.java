package JdbcTest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public class ProduitDaoImpl implements IProduitDao {
	
//	  @PersistenceContext(name = "produitsDB" )
//	  private EntityManager entityManager;

	// Création de l'objet EntityManager Factory qui lit le fichier persistence.xml
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("produitsDB");
	// Création de l'objet EntityManager
	EntityManager entityManager = entityManagerFactory.createEntityManager();

	// Sauvgarder un produit
	@Transactional
	public void saveProduct(Produit p) {
		entityManager.persist(p);
		//entityManager.flush();
	}

	// Consulter tout les produits
	@Transactional
	public List<Produit> findAll() {
		Query query = entityManager.createQuery("select p from Produit p");
		return query.getResultList();
	}

	// Consulter tout les produits par mot clé
	@Transactional
	public List<Produit> findByDesignation(String mc) {
		Query query = entityManager.createQuery("select p from Produit p where p.designation like :x");
		query.setParameter("x", "%" + mc + "%");
		return query.getResultList();
	}

	// Consulter un produit
	@Transactional
	public Produit findById(int idProduit) {
		Produit p = entityManager.find(Produit.class, idProduit);
		return p;
	}

	// Mettre àjour un produit
	@Transactional
	public void updateProduct(Produit p) {
		entityManager.merge(p);
	}

	// Supprimer un produit
	@Transactional
	public void deleteById(int idProduit) {
		Produit p = entityManager.find(Produit.class, idProduit);
		entityManager.remove(p);
	}
}
