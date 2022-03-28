package sacDoc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GestionAppli extends Main {

    
    public GestionAppli() {}

    
    public static final String DATA_PATH = "data/";

    public static final int PRECISION = 0;

    public static ArrayList<Objet> Banque(String chemin) {
        try {
			return ListeObjet(chemin);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public static List<String> ListeSource() {
   	 File directory = new File(DATA_PATH);
        if (! directory.exists())
            directory.mkdir();

        List<String> sources = null;
        try (Stream<Path> walk = Files.walk(Paths.get(DATA_PATH))) {

            sources = walk.map(Path::toString)
                    .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());
            return sources;

   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return sources;
        }
    
    public void ValiderArgsNew(String[] args) throws IllegalArgumentException{
        if (args.length < 2) {
            String msg = "Arguments Manquants : ";
            switch(args.length){
                case 0:
                    msg += "poid min ";
                case 1:
                    msg += "poids maximal ";
                default:
                    break;
            }
            throw new IllegalArgumentException(msg);
        }
        else {
            for (int i = 0; i < args.length; i++) {
                switch (i) {
                    case 0:
                    	try {
                    		POIDS_MIN = Double.parseDouble(args[i]);
                        } catch (NumberFormatException PoidsNonNumerique) {
                            throw new IllegalArgumentException("Valeur du paramètre du poids minimal est invalide: " + args[i]);
                        }
                    case 1:
                        try {
                            POIDS_MAX = Double.parseDouble(args[i]);
                        } catch (NumberFormatException PoidsNonNumerique) {
                            throw new IllegalArgumentException("Valeur du paramètre du poids maximal invalide: " + args[i]);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    
    public static ArrayList<Objet> ListeObjet(String chemin) throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            int NLignes = 0;
            ArrayList<Objet> ListeObjets = new ArrayList<>();
            while ((ligne = bf.readLine()) != null) {
            	NLignes++;
                String[] objetElement = ligne.split(";");
                if (objetElement.length != 3) {
                    throw new IOException("Syntaxe invalide lors de la lecture du fichier ligne " + NLignes
                            + "\nSyntaxe: Nom Objet ; Poids ; Prix\nExemple: Lampe ; 2.0 ; 30.0");
                }
                for (int i = 0; i < 3; ++i)
                	objetElement[i] = objetElement[i].trim().replaceAll("\n ", "");
                ListeObjets.add(new Objet(objetElement[0],
                        (Double.parseDouble(objetElement[1]) * Math.pow(10.0, PRECISION) / Math.pow(10.0, PRECISION)),
                        (Double.parseDouble(objetElement[2]) * Math.pow(10.0, PRECISION) / Math.pow(10.0, PRECISION))
                ));
            }
            return ListeObjets;
        } catch (FileNotFoundException e) {
            throw new IOException("Ouverture du fichier impossible");
        } catch (NumberFormatException PoidsNonNumerique) {
            throw new IOException("Erreur de lecture du nombre, les virgules se notent '.'.\n"
                    + "Syntaxe: Nom Objet ; Poids ; Prix\nExemple: Lampe ; 2.0 ; 30.0\n"
                    + PoidsNonNumerique.getMessage());
        }
    }
    
}
