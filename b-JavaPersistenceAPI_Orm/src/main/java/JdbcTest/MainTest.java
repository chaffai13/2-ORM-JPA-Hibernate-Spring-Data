package JdbcTest;

import java.util.List;

public class MainTest {
	
	public static void main(String[] args) throws Exception {

		//IProduitDao produitDaoImpl = new ProduitDaoImpl();
		ProduitDaoImplWithTransaction produitDaoImpl = new ProduitDaoImplWithTransaction();

		// 1) Ajouter un produit
		produitDaoImpl.saveProduct(new Produit("MSI gt72", 1200, 5));
		System.out.println("********************************************************************");		
		
		// 2) Consulter tout les produits
		List<Produit> produits = produitDaoImpl.findAll();

		for (Produit p : produits) {
			System.out.println(p.toString());
		}
		System.out.println("********************************************************************");		
		
		// 3) Consulter tout les produits par mot clé
		List<Produit> produits2 = produitDaoImpl.findByDesignation("i");

		for (Produit p2 : produits2) {
			System.out.println(p2.toString());
		}
		System.out.println("********************************************************************");			
		
		// 4) trouver Produit par son Id		
		Produit p3 = produitDaoImpl.findById(3);
		p3.toString();
		System.out.println("********************************************************************");		
		
		// 5) mettre à jour un produit
		produitDaoImpl.updateProduct(new Produit(2, "HP 1100", 3500, 2));
		System.out.println("********************************************************************");		
		
		// 6) Supprimer Produit
		//produitDaoImpl.deleteById(5);

	
	}

}
