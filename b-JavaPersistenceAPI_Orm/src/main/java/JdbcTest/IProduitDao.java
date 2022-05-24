package JdbcTest;

import java.util.List;

public interface IProduitDao {

	// Sauvgarder un produit
	public void saveProduct(Produit p);

	// Consulter tout les produits
	public List<Produit> findAll();

	// Consulter tout les produits par mot clé
	public List<Produit> findByDesignation(String mc);

	// Consulter un produit
	public Produit findById(int idProduit);

	// Mettre àjour un produit
	public void updateProduct(Produit p);

	// Supprimer un produit
	public void deleteById(int idProduit);

}
