
package cryptoTP;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 

/** <h2>readFile</h2>
 *  Classe permettant de lire les fichiers .txt generes à l'execution de CryptedArray
 * @author Obame Obame Melchior,Carlier Florian,Leclerc Baptiste, Ouahmad Kevin      ENSISA UHA
 * @version 15 janvier 2019 
 */


public class readFile {
	/** lecteur */
	private BufferedReader reader ;
	/** liste contenant toutes les lignes du fichiers .txt de lecture*/
	private String[] stringList;
	/** Nombre de lignes  du fichier à lire*/
	private String line;
	/** Nombre de colonnes du fichier à lire*/
	private String column;
	
	/**
	 * Constructeur de la classe readFile. Permet la lecture du fichier nommé fileName  
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public readFile(String fileName) throws FileNotFoundException {
		this.reader = new BufferedReader(new FileReader(new File(fileName)));
		try {
            line=reader.readLine();
            column=reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.stringList= new String[Integer.parseInt(line)*Integer.parseInt(column)];
	}
	
	/**
	 * Recupere le nombre de ligne lues.
	 * @return
	 */
	public int getLine() {return Integer.parseInt(line);}
	
	/**
	 * Recupere le nombre de colonnes lues.
	 * @return
	 */
	public int getColumn() {return Integer.parseInt(column);}
	
	/**
	 * renvoie la liste des lignes du fichier lues
	 * @return
	 */
	public String[] getStringList() {return stringList;}	
	
	/**
	 * fermeture du lecteur
	 * @throws IOException
	 */
	public void close() throws IOException {reader.close();}

	/**
	 * lecture du fichier ligne par ligne et remplissage de la liste lue.
	 */
    public void lecture(){
        try {
            String ligne;
            int k=0;
            while((ligne = reader.readLine()) != null){
                    this.stringList[k]=ligne;
                    k++;
            }
        } catch (Exception e){
        	e.printStackTrace();
        }
    }
  
}