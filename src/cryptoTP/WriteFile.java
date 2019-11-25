package cryptoTP;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/** <h2>WriteFile</h2>
 *  Classe permettant d'ecrire dans le fichier de Cryptage
 * @author Obame Obame Melchior,Carlier Florian,Leclerc Baptiste, Ouahmad Kevin      ENSISA UHA
 * @version 15 janvier 2019 
 */


public class WriteFile
{
	/** flux de sortie du fichier*/
	private FileOutputStream fos;
	
	/**
	 * Constructeur
	 * @throws FileNotFoundException
	 */
	public WriteFile() throws FileNotFoundException{
		this.fos = new FileOutputStream(new File("CryptedFile.txt"));
	}
	
	/**
	 * Permet d'ecrire une string dans le fichier a creer sans passage a la ligne
	 * @param s
	 * @throws IOException
	 */
	public void writeString(String s) throws IOException { 
        fos.write((s+"\n").getBytes());		
	}
	/**
	 * Permet d'ecrire une string dans le fichier a creer avec passage a la ligne
	 * @param s
	 * @throws IOException
	 */
	public void writeString2(String s) throws IOException { 
        fos.write((s+"\n").getBytes());		
	}
	/**
	 * fermeture du flux de sortie
	 * @throws IOException
	 */
	public void close() throws IOException {this.fos.close();}
	
	/**
	 * permet d'ecrire les dimensions du damier avec passage successif a la ligne
	 * @param i
	 * @param j
	 * @throws IOException
	 */
	public void writeDimension(int i, int j) throws IOException {
		fos.write((i+"\n"+j+"\n").getBytes());
	}
}