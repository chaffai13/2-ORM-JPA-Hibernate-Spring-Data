package JdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ObjectRelationalMapping {
	
	public List<Produit> findAllProduct() throws Exception {

		List<Produit> produits = new ArrayList<Produit>();

		// 1) Charger le pilote JDBC (c'est une classe java qui permet de communiquer
		// avec SGBDR)
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("1) Chargement du pilote JDBC...");

		// 2) Etablir une connexion avec la BDD
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_produits", "root", "");
		System.out.println("2) Connexion à la BDD...");

		// 3) Executer une requetes SQL => Créer un object PreparedStatement
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM produits");

		// 4) Executer la requete
		ResultSet rs = ps.executeQuery();
		System.out.println("3) Execution requetes SQL...");

		// 5) Transférer les données de chaque ligne dans l'objet Produit (Mapping Objet Relationnel)
		while (rs.next()) {
			Produit p = new Produit();

			p.setId(rs.getInt("id"));
			p.setDesignation(rs.getString("designation"));
			p.setPrix(rs.getDouble("prix"));
			p.setQuantite(rs.getInt("quantite"));

			produits.add(p);
		}
		System.out.println("4) Mapping Objet Relationnel ...");
		System.out.println();
		return produits;
	}

	public List<Produit> findByDesignation(String mc) throws Exception {

		List<Produit> produits = new ArrayList<Produit>();

		// 1) Charger le pilote JDBC (c'est une classe java qui permet de communiquer
		// avec SGBDR)
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("1) Chargement du pilote JDBC...");

		// 2) Etablir une connexion avec la BDD
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_produits", "root", "");
		System.out.println("2) Connexion à la BDD...");

		// 3) Executer une requetes SQL => Créer un object PreparedStatement
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM produits WHERE designation LIKE ?");

		// Remplacer le "?" par "mc"
		ps.setString(1, "%"+mc+"%");

		// 4) Executer la requete
		ResultSet rs = ps.executeQuery();
		System.out.println("3) Execution requetes SQL...");

		// 5) Transférer les données de chaque ligne dans l'objet Produit (Mapping Objet Relationnel)
		while (rs.next()) {
			Produit p = new Produit();

			p.setId(rs.getInt("id"));
			p.setDesignation(rs.getString("designation"));
			p.setPrix(rs.getDouble("prix"));
			p.setQuantite(rs.getInt("quantite"));

			produits.add(p);
		}
		System.out.println("4) Mapping Objet Relationnel ...");
		System.out.println();
		return produits;
	}
}
