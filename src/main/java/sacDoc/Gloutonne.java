package sacDoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Gloutonne {

    /**
     * Constructeur pour instancier la classe de résolution
     *
     * @param sac le sac à résoudre
     */
	protected Sacs sac;
    /**
     * L'ensemble d'objets
     */
    ArrayList<Objet> objets;

    /**
     * Constructeur pour instancier une classe de résolution
     *
     * @param sac le sac à résoudre
     */
    public Gloutonne(Sacs sac,ArrayList<Objet> objets){
        this.sac = sac;
        this.objets = objets ;
        ArrayList<Objet> tri = new ArrayList<>(objets);
        //Tri par rapport
        //tri.sort(Objet.parRapport());
        triRapideRec(tri, 0, tri.size() - 1);

        //Ajout dans le sac
        Iterator<Objet> itTri = tri.iterator();
        while(itTri.hasNext() && sac.PoidSac() < sac.getPoids()) {
            sac.AddOjet(itTri.next());
        }
    }
    
    public Gloutonne(ArrayList<Objet> objets){
        this.objets = objets ;
        ArrayList<Objet> tri = new ArrayList<>(objets);
        //Tri par rapport
        //tri.sort(Objet.parRapport());
        triRapideRec(tri, 0, tri.size() - 1);
    }

    public Sacs getSac() {
		return this.sac;
	}

	public void setSac(Sacs sac) {
		this.sac = sac;
	}

	public ArrayList<Objet> getObjets() {
		return objets;
	}

	public void setObjets(ArrayList<Objet> objets) {
		this.objets = objets;
	}

	private void triRapideRec(List<Objet> objets, int premier, int dernier) {
        if(premier < dernier) {
            int pivot = (dernier - premier) / 2 + premier;
            pivot = repartir(objets, premier, dernier, pivot);
            triRapideRec(objets, premier, pivot - 1);
            triRapideRec(objets, pivot + 1, dernier);
        }
    }

    
    private int repartir(List<Objet> objets2,int premier, int dernier, int pivot) {
        Collections.swap(objets2, pivot, dernier);
        int i = premier;
        for (int j = premier; j < dernier; ++j) {
            if(objets2.get(j).getRapport() >= objets2.get(dernier).getRapport()) {
                Collections.swap(objets2, j, i);
                i++;
            }
        }
        Collections.swap(objets2, dernier, i);
        return i;
    }
}