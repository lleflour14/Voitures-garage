package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (!myStationnements.isEmpty() && myStationnements.get(myStationnements.size()-1).estEnCours()) {
			throw new IllegalArgumentException("la voiture est deja dans le garage");

		}
		else{
			Stationnement s = new Stationnement(this, g);
			myStationnements.add(s);

		}
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (!myStationnements.isEmpty()) {


			if (myStationnements.get(myStationnements.size()-1).estEnCours()) {
				myStationnements.get(myStationnements.size()-1).terminer();
			} else {
				throw new IllegalArgumentException("la voiture n'est pas dans un garage");
			}
		}
		else{
			throw new IllegalArgumentException("Il n'y a pas de stationnement");

		}
	}

		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement


	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> g = new HashSet<>();
		for(Stationnement s: myStationnements){
			g.add(s.getGarage());

		}
		return g;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {

		if (!myStationnements.isEmpty() && myStationnements.get(myStationnements.size()-1).estEnCours()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		for (Garage g : garagesVisites()){
			out.println(g.toString());
			for(Stationnement s :myStationnements){
				if(s.getGarage().equals(g)){
					out.println(s.toString());
				}
			}
		}
	}

}
