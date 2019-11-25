package cryptoTP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
/** <h2>CryptedArray</h2>
 * classe principale du projet , cette classe permet à la fois de creer l'interface graphique et 
 * de faire simultanement le cryptage ou le decryptage
 * @author Obame Obame Melchior,Carlier Florian,Leclerc Baptiste, Ouahmad Kevin      ENSISA UHA
 * @version 15 janvier 2019 
 */
public class CryptedArray extends JFrame {

	private static final long serialVersionUID = 1L;
	/** matrice de double .Represente le damier iniial*/
	private double[][] array;
	/** matrice de tableau de byte .Représente le damier crypté*/
	private byte[][][]  cryptedArr;
	/** vecteur d'initialisation */
	private static IvParameterSpec initVector;
	
	//_______________________________ GRAPHISME ____________________________
	private StringBuilder details;
	private StringBuilder detailsDecryptage;
   	private void initComponents(){
       jPanel1 = new javax.swing.JPanel();
       ligne = new javax.swing.JLabel();
       colone = new javax.swing.JLabel();
       CryptButton = new javax.swing.JButton();
       DecryptButton = new javax.swing.JButton();
       jLabel1 = new javax.swing.JLabel();
       jLabel2 = new javax.swing.JLabel();
       jLabel3 = new javax.swing.JLabel();
       jSeparator1 = new javax.swing.JSeparator();
       jSeparator2 = new javax.swing.JSeparator();
       panelRapport = new javax.swing.JPanel();
       jScrollPane1 = new javax.swing.JScrollPane();
       rapportArea = new javax.swing.JTextArea();
       jLabel4 = new javax.swing.JLabel();
       afficherButton = new javax.swing.JButton();
       jMenuBar1 = new javax.swing.JMenuBar();
       jMenu1 = new javax.swing.JMenu();
       jMenu2 = new javax.swing.JMenu();

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

       jPanel1.setBackground(new java.awt.Color(193, 221, 254));

       ligne.setBackground(new java.awt.Color(188, 182, 175));
       ligne.setText("0");

       colone.setText("0");

       CryptButton.setText("Crypter");
       CryptButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               try {
				CryptButtonActionPerformed(evt);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
           }
       });

       DecryptButton.setText("Decrypter");
       DecryptButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               try {
				DecryptButtonActionPerformed(evt) ;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
           }
       });

       jLabel1.setText("Nombre  ligne :");

       jLabel2.setText("Nombre colonne:");

       jLabel3.setFont(new java.awt.Font("AnjaliOldLipi", 1, 24)); // NOI18N
       jLabel3.setText("Groupe_9:AES mode CBC");

       panelRapport.setBackground(new java.awt.Color(192, 192, 191));

       rapportArea.setColumns(20);
       rapportArea.setRows(5);
       rapportArea.setFont(new java.awt.Font("Ubuntu", 0, 16));
       rapportArea.setForeground(new java.awt.Color(34, 120, 14));
       rapportArea.setSelectionColor(new java.awt.Color(33, 133, 213));
       rapportArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
       jScrollPane1.setViewportView(rapportArea);

       javax.swing.GroupLayout panelRapportLayout = new javax.swing.GroupLayout(panelRapport);
       panelRapport.setLayout(panelRapportLayout);
       panelRapportLayout.setHorizontalGroup(
           panelRapportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
       );
       panelRapportLayout.setVerticalGroup(
           panelRapportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
       );

       jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
       jLabel4.setText("détails :");

       afficherButton.setText("Damier Original");
       afficherButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               try {
				afficherButtonActionPerformed(evt);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
           }
       });

       javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
       jPanel1.setLayout(jPanel1Layout);
       jPanel1Layout.setHorizontalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jSeparator1)
           .addComponent(jSeparator2)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addContainerGap()
                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addGroup(jPanel1Layout.createSequentialGroup()
                               .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               .addComponent(ligne, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                           .addGroup(jPanel1Layout.createSequentialGroup()
                               .addComponent(jLabel2)
                               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                               .addComponent(colone, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                       .addGap(151, 151, 151)
                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                           .addGroup(jPanel1Layout.createSequentialGroup()
                               .addComponent(CryptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addGap(18, 18, 18)
                               .addComponent(DecryptButton))
                           .addComponent(afficherButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addContainerGap()
                       .addComponent(panelRapport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addGap(171, 171, 171)
                       .addComponent(jLabel3))
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addContainerGap()
                       .addComponent(jLabel4)))
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       jPanel1Layout.setVerticalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addGap(8, 8, 8)
               .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(DecryptButton)
                   .addComponent(CryptButton)
                   .addComponent(jLabel1)
                   .addComponent(ligne))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(jLabel2)
                   .addComponent(colone)
                   .addComponent(afficherButton))
               .addGap(20, 20, 20)
               .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(jLabel4)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(panelRapport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGap(24, 24, 24))
       );

       jMenuBar1.setBackground(new java.awt.Color(105, 105, 104));
       jMenuBar1.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N

       jMenu1.setText("Fichier");
       jMenuBar1.add(jMenu1);

       jMenu2.setText("Edit");
       jMenuBar1.add(jMenu2);

       setJMenuBar(jMenuBar1);

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       );

       pack();
   }// </editor-fold>

   	//_________________________ fin de la partie graphique _________________
   	
   	/**
   	 * Constructeur de la classe principale.
   	 * @param array
   	 * @param aesKey
   	 * @throws NoSuchAlgorithmException
   	 * @throws NoSuchPaddingException
   	 * @throws InvalidKeyException
   	 * @throws IllegalBlockSizeException
   	 * @throws BadPaddingException
   	 * @throws InvalidAlgorithmParameterException
   	 * @throws IOException
   	 */
	public CryptedArray(double[][] array, byte[] aesKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException  {
		initComponents();
		details = new StringBuilder();
		detailsDecryptage= new StringBuilder("\n---------------------\n"+ "Décryptage AES...\nRécupération de la clé...\nRecupération du Cipher...\nOuverture de DecriptFile.txt...\n");
		this.array=array;
		this.setVisible(true);
		refresh("Initialisation Encryptage AES ...\n");
		refresh("Génération de l'iv...\n");
		
		// Generation de IV.
        int ivSize = 16;
        byte[] initVector = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(initVector);
        IvParameterSpec iv = new IvParameterSpec(initVector);
        CryptedArray.initVector = iv;
            
        refresh("Création Cipher...\n");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		
		refresh("Création de la clé sécrète...\n");
		refresh("CLE : "+new String(Base64.getEncoder().encode(aesKey))+"\n");
		SecretKeySpec secretkeySpec = new SecretKeySpec(aesKey, 0, aesKey.length, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretkeySpec,iv);

		int I=array.length;
		int J=array[0].length;
		ligne.setText(I+"");
		colone.setText(J+"");
		byte[][][] cryptedArrTemp = new byte[I][J][];
		refresh("debut cryptage Grille...\n");
		
		WriteFile wf =new WriteFile();
		WriteDamier wd= new WriteDamier();
		
		refresh("Ouvreture fichier CryptedFile.txt...\n");
		wf.writeDimension(I, J);
		wd.writeDimension(I, J);
		for (int i = 0; i <I; i++) {
			for (int j = 0; j < J; j++) {
				byte[] arrayBytes= new byte[16];				//8 octets pour un double 64bits
				ByteBuffer.wrap(arrayBytes).putDouble(array[i][j]);
				if(((i+j)%2)==0){
					cryptedArrTemp[i][j] = cipher.doFinal(arrayBytes);
				}
				else{
					 cryptedArrTemp[i][j]=arrayBytes;
				}
				String s=new String(Base64.getEncoder().encode(cryptedArrTemp[i][j]));
				refresh("i:"+i+" j:"+j+"-->"+s+"\n");
				wf.writeString(s);
				wd.writeString(array[i][j]+"");;
			}
		}
		refresh("ecriture dans CryptedFile.txt...\n");
		refresh("fin Cryptage Grille...\n");
		refresh("fermeture fichier CrypteFile.txt...\n");
		wf.close();
		wd.close();
		this.cryptedArr=cryptedArrTemp;
	}
	
	
	/**
	 * Permet de decrypter le byte[] qui est en position i(ligne) et j(colonne)
	 * @param aesKey
	 * @param i
	 * @param j
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public double decrypt(byte[] aesKey, int i, int j) throws UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		double result=0d;
		SecretKeySpec secretkeySpec = new SecretKeySpec(aesKey, 0, aesKey.length, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretkeySpec,CryptedArray.initVector);   
        if((i+j)%2==0) {
        	byte[] resultByte=cipher.doFinal(this.cryptedArr[i][j]);
        	result= ByteBuffer.wrap(resultByte).getDouble();
        	refreshDecryptage("decryptage case["+i+"]["+j+"]"+"-->"+result+"\n");
			return result;
        }
        else {
        	double tmp=ByteBuffer.wrap(cryptedArr[i][j]).getDouble();
        	refreshDecryptage("case["+i+"]["+j+"]"+"-->"+tmp+"\n");
        	return tmp;
        } 
	}
	
	/**
	 * affiche les details dans le JPanel dedie dans la premiere partie du cryptage.
	 * @param s
	 */
	public void refresh(String s) {
		details.append(s);
		rapportArea.setText(details.toString());
	}
	
	/**
	 * affiche les details dans le JPanel dedie dans la seconde partie du decryptage.
	 * @param s
	 */
	public void refreshDecryptage(String s) {
		detailsDecryptage.append(s);
		details.append(detailsDecryptage.toString());
		detailsDecryptage=new StringBuilder();
		rapportArea.setText(details.toString());
		
	}
	
	/**
	 * Definit l'action associee au bouton Crypter. permet d'afficher la grille cryptee
	 * @param evt
	 * @throws FileNotFoundException
	 */
	private void CryptButtonActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {
		new GrilleDamier("CryptedFile.txt");
	}

	/**
	 * Definit l'action associée au bouton Decrypter. permet d'afficher la grille décryptée
	 * @param evt
	 * @throws FileNotFoundException
	 */
	private void DecryptButtonActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {
		new GrilleDamier("DecryptedFile.txt");
	}

	/**
	 * Definit l'action associée au bouton Afficher. permet d'afficher la grille generée  aléatoirement
	 * @param evt
	 * @throws FileNotFoundException
	 */
	private void afficherButtonActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException {//GEN-FIRST:event_afficherButtonActionPerformed
		new GrilleDamier("Damier.txt");
	}
	
	/**
	 * Méthode de lancemnt du programme . Génere aleatoirement la grille et la clé AES , lance le cryptage et le  décryptage.
	 * @param args
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CryptedArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CryptedArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CryptedArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CryptedArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		 
		int I = ThreadLocalRandom.current().nextInt(3, 10);
		int J = ThreadLocalRandom.current().nextInt(3, 12);
		
		double[][] array=new double[I][J] ;
		for (int i = 0; i < I; i++) {
			for (int j = 0; j < J; j++) {
				array[i][j]=Double.MIN_VALUE + (Double.MAX_VALUE - Double.MIN_VALUE) * new Random().nextDouble();
			}
		}
				
		// creation de la clef AES
		byte[] aesKey=null; 
		KeyGenerator keyGen;
		try{
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			aesKey = keyGen.generateKey().getEncoded();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		CryptedArray cryptdarray= new CryptedArray(array, aesKey);
		cryptdarray.rapportArea.setText(cryptdarray.details.toString());
		WriteFileDecrypted wf = new WriteFileDecrypted();
		wf.writeDimension(cryptdarray.array.length, cryptdarray.array[0].length);
		for (int k = 0; k < cryptdarray.array.length; k++) {
			for (int l = 0; l < cryptdarray.array[0].length; l++) {
				double decr=0;
				decr=cryptdarray.decrypt(aesKey, k, l);
				wf.writeString(decr+"");
				cryptdarray.refreshDecryptage("fermeture de DecryptFile.txt...\n"+"fin du décryptage...\n");				
			}
		}
		wf.close();
	}

	   // Variables declaration - do not modify//GEN-BEGIN:variables
		private javax.swing.JButton CryptButton;
	    private javax.swing.JButton DecryptButton;
	    private javax.swing.JButton afficherButton;
	    private javax.swing.JLabel colone;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JMenu jMenu1;
	    private javax.swing.JMenu jMenu2;
	    private javax.swing.JMenuBar jMenuBar1;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JSeparator jSeparator1;
	    private javax.swing.JSeparator jSeparator2;
	    private javax.swing.JLabel ligne;
	    private javax.swing.JPanel panelRapport;
	    private javax.swing.JTextArea rapportArea;
	   // End of variables declaration//GEN-END:variables

}
