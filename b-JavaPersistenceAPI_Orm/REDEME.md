1)Créer un projet Spring « b-JavaPersistenceAPI_Orm » et ajouter dans le pom.xml les dépendances suivantes :
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
			<version>2.7.0</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.29</version>
		</dependency>

2)  créer la classe Produit (Entité JPA => Java Bean)

	- Ajouter l’annotation « @Entity » pour que la classe soit une persistante (entité JPA) => correspond à une table dans la BDD
	- Ajouter l’annotation « @Table » pour spécifier le nom de la table
	- Ajouter l’annotation « @Id » pour spécifier la clé primaire et pour que la valeur soit générer automatiquement.
	- Il faut ajouter l’annotation « GeneratedValue(strategy = GenerationType.IDENTITY»
	- Ajouter l’annotation « @Column » pour spécifier le nom de la colonne 

3)  Ajouter l'implémentation Serializable à la classe Produit

	- Sérialisation = Prendre un objet de la mémoire (RAM) et le convertir à un tableau d’octet (BYTE) puis l’envoyer vers une autre application
	- Désérialisation = Prendre un tableau d’octet (BYTE) et le convertir à un objet
	- Hibernate crée les objets dans la mémoire et si celle-ci est saturée il va prendre les objets de la ram et le mettre dans un fichier (cache) pour 	libérer la mémoire (sérialisation)
	
4) Ajouter les paramètres de configuration de l’unité de persistance dans le fichier persistence.xml

	<persistence-unit name="sample">
	  <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
	  <properties>
		 <property name="hibernate.connection.url" value="jdbc:mysql://<hostname>/<database>"/>
		 <property name="hibernate.connection.username" value="root"/>
		 <property name="hibernate.connection.password" value=""/>
		 <property name="hibernate.connection.driver_class" value="com.mysql.jdbc.Driver"/>
		 <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
		 <property name="hibernate.hbm2ddl.auto" value="update"/>
		 <property name="hibernate.show_sql" value="true"/>		 
	  </properties>
	</persistence-unit>
	
5) Pour gérer les entités JPA (Ajouter, Lire, Mettre à jour, Supprimer, …) il faut :

	- Créer un objet EntityManagerFactory  et spécifier le nom de l’unité de persistance (<persistence-unit name="sample">)
	- Créer l’objet entityManager à partir EntityManagerFactory
	- Créer les méthodes Ajouter, Lire, Mettre à jour, Supprimer, …
