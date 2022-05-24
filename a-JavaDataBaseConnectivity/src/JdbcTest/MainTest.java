package JdbcTest;

import java.util.List;

public class MainTest {

	public static void main(String[] args) throws Exception {

		ObjectRelationalMapping orm = new ObjectRelationalMapping();

		List<Produit> produits = orm.findAllProduct();

		for (Produit p : produits) {
			System.out.println(p.toString());
		}
		
		List<Produit> produitsMc = orm.findByDesignation("i");

		for (Produit pMc : produitsMc) {
			System.out.println(pMc.toString());
		}
	}

}
