package cryptoTP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/** <h2>WriteDamier</h2>
 *  Classe permettant d'ecrire dans le fichier du damier à l'etat initial
 * @author Obame Obame Melchior,Carlier Florian,Leclerc Baptiste, Ouahmad Kevin      ENSISA UHA
 * @version 15 janvier 2019 
 */

public class WriteDamier {
	/** flux de sortie du fichier*/
	private FileOutputStream fos;
	
	/**
	 * Constructeur
	 * @throws FileNotFoundException
	 */
	public WriteDamier() throws FileNotFoundException{
		this.fos = new FileOutputStream(new File("Damier.txt"));
	}
	
	/**
	 * Permet d'ecrire une string dans le fichier à creer sans passage a la ligne
	 * @param s
	 * @throws IOException
	 */
	public void writeString(String s) throws IOException { 
        fos.write((s+"\n").getBytes());		
	}
	
	/**
	 * Permet d'ecrire une string dans le fichier à creer avec passage a la ligne
	 * @param s
	 * @throws IOException
	 */
	public void writeString2(String s) throws IOException { 
        fos.write((s+"\n").getBytes());		
	}
	
	/**
	 * fermeture du flux
	 * @throws IOException
	 */
	public void close() throws IOException {this.fos.close();}
	
	/**
	 * permet d'ecrire les dimensions du damier avec passage successif à la ligne
	 * @param i
	 * @param j
	 * @throws IOException
	 */
	public void writeDimension(int i, int j) throws IOException {
		fos.write((i+"\n"+j+"\n").getBytes());
	}
	
}
