package cryptoTP;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/** <h2>GrilleDamier</h2>
 *  Classe permettant de generer les damiers en se servant des fichiers .txt  generes
 * @author Obame Obame Melchior,Carlier Florian,Leclerc Baptiste, Ouahmad Kevin      ENSISA UHA
 * @version 15 janvier 2019 
 */
 
public class GrilleDamier extends JFrame {

	private static final long serialVersionUID = 1L;
	/** taille du damier , lue dans le fichier fileName passé en parametre du constructeur*/
	int taille;
	/** matrice  de JPanel representant les cases du damiers */
    JPanel [][]tab;
    /** Panel du damier*/
    Container cont=getContentPane();
    
    /**
     * Constructeur , genere le damier a partir du type de fichier passé en parametre
     * @param fileName
     * @throws FileNotFoundException
     */
    public GrilleDamier(String fileName) throws FileNotFoundException{
    	readFile rf = new readFile(fileName); //nom du fichier qu'on veut visualiser, a rectifier aussi dans le readFile en cas de modification
    	int I, J;
    	I=rf.getLine();
    	J=rf.getColumn();
    	String[] stringlist=new String[I*J];
    	rf.lecture();
    	stringlist= rf.getStringList();
    	
    	JPanel [][]tab=new JPanel[I][J]; //tableau de tableau de panel
    	int taille = 120;  // arete d'une case
    	
        if(fileName.equals("CryptedFile.txt")) {
        		setTitle("Damier Crypté");
	       		setLocation(150, 130);
        }
        if(fileName.equals("DecryptedFile.txt")){
        		setTitle("Damier Decrypté");
        		setLocation(200, 180);
        }
        else if(fileName.equals("Damier.txt")){
    		setTitle("Damier Initial");
    		setLocation(240, 220);
        }
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(J*taille,I*taille));
 
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(I,J)); 
        int k=0;
        for(int i=0;i<=(I-1);i++)
        	for(int j=0;j<=(J-1);j++){
        			tab[i][j]=new JPanel();
        			JTextArea textArea = new JTextArea();
        			textArea.setLineWrap(true);
        			textArea.setLocation(0, 10);
        			textArea.setWrapStyleWord(false);
        			textArea.setEditable(false);
        			p1.add(tab[i][j]);
        			
        			if(((i+j)%2)==0) {
                        tab[i][j].setBackground(Color.blue);
                        textArea.setText(stringlist[k]);
                        textArea.setBackground(Color.blue);
                        k++;
        				}
                    else {
                        tab[i][j].setBackground(Color.white);
                        textArea.setText(stringlist[k]);
                        k++;
                    }									
        		tab[i][j].add(textArea);
        	}
        cont.add(p1);
        pack();
        setVisible(true);
    }
 
}