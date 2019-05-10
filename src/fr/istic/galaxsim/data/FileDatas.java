package fr.istic.galaxsim.data;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * classe qui permet de lire le contenu d'un fichier
 * @author anaofind
 *
 */
public class FileDatas {
	
	
	/** 
	 * le reader du fichier
	 */
	private Scanner reader;
	
	/**
	 * le chemin du fichier
	 */
	private String pathFile;
	
	/**
	 * separateur de block
	 */
	private String separatorBloc = "\n";

	private int bytesRead = 0;

	private long fileLength = 0;
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 */
	public FileDatas(String pathFile){
		this.pathFile = pathFile;
	}
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 * @param separatorBloc le separateur de bloc
	 */
	public FileDatas(String pathFile, String separatorBloc){
		this.pathFile = pathFile;
		this.separatorBloc = separatorBloc;
	}
	
	/**
	 * méthode qui permet d'ouvrire le fichier
	 */
	public void openFile(){
		try {
			if (! this.isFileExist()){
				throw new Exception("Le fichier n'existe pas");
			}
			
			this.initReader();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	/**
	 * methode permettant de retourner le bloc suivant du fichier
	 * @return le bloc suivante du fichier
	 */
	public String nextBloc(){
		if (reader.hasNext()){
			
			String bloc = "";
	
			Scanner sc = new Scanner(reader.next());
			if (sc.hasNext()){
				bloc += sc.next();
				while (sc.hasNext()){
					bloc += " " + sc.next();
				}
				sc.close();
				return bloc;
			}
			sc.close();
		}
		return null;
	}
	
	/**
	 * methode permettant de retourner le bloc suivant du fichier (en conservant les espaces)
	 * @return le bloc suivante du fichier
	 */
	public String nextOriginalBloc(){
		if (reader.hasNext()){
			
			String bloc = reader.next();
			return bloc; 
		}
		return null;
	}
	
	/**
	 * methode permettant de retourner la ligne suivante du fichier
	 * @return la ligne suivante du fichier
	 */
	public String nextLine(){
		if (reader.hasNext()){
			String line = "";
			Scanner sc = new Scanner(reader.nextLine());
			if (sc.hasNext()){
				line += sc.next();
				while (sc.hasNext()){
					line += " " + sc.next();
				}
				sc.close();
				return line;
			}
			sc.close();
		}
		return null;
	}
	
	
	/**
	 * methode permettant de retourner la ligne suivante du fichier (en conservant les espaces)
	 * @return la ligne suivante du fichier
	 */
	public String nextOriginalLine(){
		if (reader.hasNext()){
			String line = reader.nextLine();
			bytesRead += line.length();
			return line;
		}
		return null;
	}
	
	/**
	 * methode permettant de retourner le mot suivant du fichier
	 * @return le mot suivant du fichier
	 */
	public String nextWord(){
		if (reader.hasNext()){
			return reader.next();
		}
		return null;
	}
	
	/**
	 * méthode qui permet de fermer le fichier
	 */
	public void closeFile(){
		if (reader != null){
			reader.close();
			reader = null;	
		}
	}

	/**
	 * méthode qui permet de savoir si le fichier est ouvert
	 * @return, un booléen
	 */
	public boolean isOpen(){
		return  (reader != null);
	}


	/**
	 * méthode qui permet de vérfier que le fichier existe
	 * @return booléen
	 */
	private boolean isFileExist(){
		File file = new File(this.pathFile);
		return (file.isFile() && !file.isDirectory());
	}
	
	/**
	 * méthode qui permet de preparer le reader
	 */
	private void initReader(){
		File file = new File(this.pathFile);
		fileLength = file.length();
		try {
			this.reader = new Scanner(file);
			this.reader.useDelimiter("\\s*" + this.separatorBloc + "\\s*");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public int getBytesRead() {
		return bytesRead;
	}

	public long getFileLength() {
		return fileLength;
	}
}
