package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private int nbEtal;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nbEtal = nbEtal;
		marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les legendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String Produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " chercher un endroit pour vendre " + nbProduit + " " + Produit + " \n");
		int x = marche.trouverEtalLibre();
		marche.utiliserEtal(x, vendeur, Produit, nbProduit);
		x++;
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des fleurs a l'etal " + x + " \n");
		return chaine.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] rechercheProduit= marche.trouverEtals(produit);
		chaine.append("Les vendeurs qui proposent des" + produit + " sont : \n");
		for (int i = 0; i < rechercheProduit.length; i++) {
			if (rechercheProduit[i]!= null) {
				chaine.append("- " + rechercheProduit[i].getVendeur().getNom());
			}
		}
		return chaine.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le vendeur "+ vendeur.getNom() +" quitte son etal, il a vendu " + "20"+" parmi les " + "20"+ " qu'il voulait vendre. \n");
		chaine.append(marche.trouverVendeur(vendeur).libererEtal());
		return chaine.toString();
	}
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		Etal[] etal= marche.etals;
		chaine.append("Le marche du village"+ nom +" possede plusieurs etals : \n");
		for (int i=0;i<etal.length;i++) {
			if (etal[i].isEtalOccupe())
			chaine.append(etal[i].afficherEtal());
		}
		chaine.append(marche.afficheMarche());
		return chaine.toString();
	}
	private static class Marche {
		private Etal[] etals;

		private Marche(int nbrEtals) {
			etals = new Etal[nbrEtals];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;
		}

		private Etal[] trouverEtals(String produit) {
			Etal[] etalnew = new Etal[etals.length];
			int j = 0;
			for (int i = 0; i < etals.length-1; i++) {
				if (etals[i].isEtalOccupe()) {
					if (etals[i].contientProduit(produit) == true) {
						etalnew[j] = etals[i];
						j++;
					}
				}
			}
			return etalnew;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if ((etals[i].getVendeur()).getNom() == gaulois.getNom())
					return etals[i];
			}
			return null;
		}

		private String afficheMarche() {
			int nbEtalVide = etals.length;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() == true) {
					etals[i].afficherEtal();
					nbEtalVide--;
				}
			}
			return ("Il reste " + nbEtalVide + " etals non utilises dans le marche.\n");
		}
	}

}