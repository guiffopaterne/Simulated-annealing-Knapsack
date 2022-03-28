/**
 * 
 */
package sacDoc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GUIFFO WAFFO Paterne
 *
 */
public class Main {
	public static final int PRECISION = 0;
    /**
     * Le Poids Maximal du sac
     */
    public static Double POIDS_MAX;
    /**
     * Le Poids Minimal du sac
     */
    public static Double POIDS_MIN;
    public static final String DATA_PATH = "data/";
    public static  String FILENAME = "filename.csv";
    public static List<String> CHEMINS;

    
    public static void main(String[] args) throws IOException {
    	File directory = new File("output");
        if (! directory.exists())
            directory.mkdir();
    	GestionAppli app = new GestionAppli();
    	app.ValiderArgsNew(args);
		File ouputFile = new File(directory+"/"+FILENAME);
		if (! ouputFile.exists())
			ouputFile.delete();
		try {
			PrintWriter writer = new PrintWriter(ouputFile);
			writer.printf("filename ,Poids_max,Valeurs_glouton,Valeur_recuit,Poids_glouton,Poids_recuit\n");
			CHEMINS = GestionAppli.ListeSource();
	    	for(String chemin:CHEMINS) {
	    		for(Double poid=POIDS_MIN;poid<POIDS_MAX;poid+=5) {
	    			Sacs sac = new Sacs(poid);
	    	        Sacs sacGlouton = new Sacs(poid);
	    	        ArrayList <Objet> Objets = GestionAppli.Banque(chemin);
	    	        if(Objets!=null) {
	    	        	System.out.println(Objets.toString());
	    	        	ArrayList <Objet> recuitObject = Objets;
	    	        	ArrayList <Objet> gloutonObject = Objets;
	    	        	RecuitSimuleSacDos recuitAl = new RecuitSimuleSacDos(recuitObject,sac,1000.0,1.0,0.6,0.4,10);
	    	        	recuitAl.RecuitSimule();
	    	        	Gloutonne glouton = new Gloutonne(sacGlouton,gloutonObject);
	    	        	sacGlouton = glouton.getSac();
	    	        	sac = recuitAl.best;
//	    				writer.printf("filename ,Poids_max,Valeurs_glouton,Valeur_recuit,Poids_glouton,Poids_recuit\n");
	    				writer.printf("%s,%s,%s,%s,%s,%s\n",chemin,poid,sacGlouton.ValeurSac(),sac.ValeurSac(),sacGlouton.PoidSac(),sac.PoidSac());
	    	        	}
	    	        }
	    	   }
			writer.close();
		}catch(FileNotFoundException e1) {
			System.err.println(e1);
		}
        
    }

}
