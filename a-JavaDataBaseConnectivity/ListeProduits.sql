CREATE TABLE Produits(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        designation VARCHAR(80) NOT NULL,
                        prix DOUBLE NOT NULL,
                        quantite INT NOT NULL
                        );
						
INSERT INTO Produits (id, designation, prix, quantite)
VALUES (1, 'Ordi HL 3421', 980, 12), (2, 'Imprimante HP LX 7600', 2300, 10), (3, 'MAC', 1200, 5);