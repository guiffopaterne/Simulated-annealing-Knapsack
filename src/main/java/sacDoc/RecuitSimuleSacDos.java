package sacDoc;

import java.util.ArrayList;
//import java.util.Random;
//import java.util.Set;

public class RecuitSimuleSacDos {
	private ArrayList<Objet> Objets;
	private Sacs sacs;
	private Double Temperature;
	private Double Ts;
	private Double probabilite;
	private Double k;
	public Sacs best;
	private int Niter;
	
	public RecuitSimuleSacDos(ArrayList<Objet> Objets,Sacs sacs, Double Temperature,Double Ts,Double probabilite,Double k,int Niter) {
		this.Objets=Objets;
		this.Temperature=Temperature;
		this.Ts=Ts;
		this.probabilite =probabilite;
		this.Niter = Niter;
		this.sacs=sacs;
		this.best=sacs;
		this.k =k;
	}
	public Double Probabilite(Double diff) {
		return Math.exp(diff/this.Temperature);
		
	}
	public Sacs InitialSolution() {
		Gloutonne glouton = new Gloutonne(this.sacs,this.Objets);
		this.sacs = glouton.getSac();
    	return glouton.getSac();
		
	}
	public Sacs NeighborSolution() {
		System.out.println("Creation du clone");
		Sacs candidat = this.sacs.clone();
		Niter =1;
		int find=0;
		Double p=0.0;
		for(int i=0;i<this.sacs.size()-1;i++) {
			if(this.sacs.getObjets().get(i).getPoids()>p) {
				p=this.sacs.getObjets().get(i).getPoids();
				find  = i;
			}
		}
		candidat.DropObjet(find);
		Gloutonne ObjetsTries = new Gloutonne(this.Objets);
		this.Objets = ObjetsTries.getObjets();
		int i =0;
		while(!candidat.isFull()&& Niter<= this.Niter && i<=this.Objets.size()-1){
			if(candidat.AddOjet(this.Objets.get(i))) {
				this.Objets.remove(i);
			}
			if(this.sacs.size()==this.Objets.size()) break;
			Niter ++;
			i++;
		}
		return candidat;
	}
	public void RecuitSimule() {
		System.out.println("intialisation de la solution");
		this.best=this.InitialSolution();
		int Niter = 1;
		while(this.Temperature>this.Ts && Niter <this.Niter) {
			Double e1 = this.sacs.ValeurSac();
			System.out.println("Recherche solution voisine ");
			Sacs candidat = this.NeighborSolution();
			Double e2 = candidat.ValeurSac();
			Double diff = e2 - e1;
			if(diff<0) {
				System.out.println("Meilleur solution");
				this.best = candidat;
			}else {
				Double p = this.Probabilite(-1*diff);
				if(p>this.probabilite) {
					System.out.println("acceptation de la solution");
					this.sacs =candidat;
				}
			}
			this.Temperature =this.Temperature*this.k;
			Niter ++;
		}
		if(this.best.ValeurSac()<this.sacs.ValeurSac()) {
			this.best = this.sacs;
		}
	}
	
	
	
	
	public Double getProbabilite() {
		return probabilite;
	}
	public void setProbabilite(Double probabilite) {
		this.probabilite = probabilite;
	}
	public Double getTs() {
		return Ts;
	}
	public void setTs(Double ts) {
		Ts = ts;
	}
	public Sacs getSacs() {
		return sacs;
	}
	public void setSacs(Sacs sacs) {
		this.sacs = sacs;
	}
	public Double getTemperature() {
		return Temperature;
	}
	public void setTemperature(Double temperature) {
		Temperature = temperature;
	}
	public ArrayList<Objet> getObjets() {
		return Objets;
	}
	public void setObjets(ArrayList<Objet> objets) {
		Objets = objets;
	}
	public Double getK() {
		return k;
	}
	public void setK(Double k) {
		this.k = k;
	}

}
