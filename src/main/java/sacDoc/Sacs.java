package sacDoc;

import java.util.ArrayList;

public class Sacs {
	public ArrayList<Objet> Objets= new ArrayList<Objet>();
	private Double Poids;

	public Sacs(ArrayList<Objet> object, Double Poids) {
		this.Objets=object;
		this.Poids = Poids;
	}
	public Sacs(ArrayList<Objet> Objets) {
		this.Objets=Objets;
	}
	public Sacs(Double Poids) {
		this.Poids = Poids;
	}

	
	public ArrayList<Objet> getObjets() {
		return Objets;
	}

	public void setObjets(ArrayList<Objet> objets) {
		Objets = objets;
	}
	public Double PoidSac() {
		Double p = 0.0;
		if(this.Objets!=null) {
			for(Objet objet : this.Objets) {
				p = p + objet.getPoids();
			}
		}
		return p;
	}
	@SuppressWarnings("unchecked")
	public Sacs clone(){
		 return new Sacs((ArrayList<Objet>) this.Objets.clone(),this.getPoids());
	}
	public Double ValeurSac() {
		Double v = 0.0;
		for(Objet objet : this.Objets) {
			v = v + objet.getValeur();
		}
		return v;
	}
	public Boolean isEmpty() {
		return this.Objets.isEmpty();
	}
	public Boolean isFull() {
		return this.PoidSac()==this.getPoids();
	}
	public Boolean DropObjet(Objet objet) {
		return this.Objets.remove(objet);
	}
	public Objet DropObjet(int objet) {
		return this.Objets.remove(objet);
	}
	
	public int size() {
		return this.Objets.size();
	}
	public boolean AddOjet(Objet objet) {
		if (this.PoidSac()+objet.getPoids()<=this.getPoids()) {
			System.out.println("les objects");
			System.out.println(objet);
			this.Objets.add(objet);
			return true;
		}
		return false;
	}
	public Double getPoids() {
		return Poids;
	}
	public void setPoids(Double poids) {
		Poids = poids;
	}
	
	
	

}
