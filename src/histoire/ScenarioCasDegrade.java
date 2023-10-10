package histoire;


import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;


public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		Gaulois Jean= new Gaulois("Jean", 8);
		etal.acheterProduit(6, Jean);
		System.out.println("Fin du test");
		}
}
