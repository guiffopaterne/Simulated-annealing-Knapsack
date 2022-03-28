package sacDoc;


public class Objet {
	private String label;
	private Double poids;
	private Double valeur;
	private Double rapport;

	public Objet(String label,Double poids,Double valeur) {
		this.label = label;
		this.poids = poids;
		this.valeur = valeur;
		this.rapport = valeur/poids;
	}
	@Override
	public String toString() {
		return "Objet label=" + label + ", \n \t poids=" + poids + ", \n \t valeur=" + valeur + "]";
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	public Double getValeur() {
		return valeur;
	}
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}
	public Double getRapport() {
		return rapport;
	}
	public void setRapport(Double rapport) {
		this.rapport = rapport;
	}

}
