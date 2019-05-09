package fr.istic.galaxsim.data;

import java.util.Observable;

/**
 * classe abstraite de parser de fichier
 * @author anaofind
 *
 */
public abstract class AbstractParserFile extends Observable{
	
	/**
	 * code de recuperation de type de donnees
	 * 0 : mots par mots
	 * 1 : ligne par ligne (un espace entre chaque mot)
	 * 2 : ligne par ligne (espaces originals)
	 * 3 : bloc par bloc (un espace entre chaque mot)
	 * 4 : bloc par bloc (espaces originals)
	 */
	public static final int DATAS_WORD = 0, DATAS_LINE = 1, DATAS_ORIGINAL_LINE = 2,  DATAS_BLOC = 3, DATAS_ORIGINAL_BLOC = 4;
	
	/**
	 * le fichier
	 */
	private FileDatas file;
	
	/**
	 * le code de recuperation de donnees
	 */
	private int codeDatas = DATAS_LINE;
	
	/**
	 * le chemin du fichier
	 */
	private String pathFile;
	
	/**
	 * le numero de la donnée en cours d'analyse
	 */
	private int numDataParsing = 0;
	
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 */
	public AbstractParserFile(String pathFile){
		this.pathFile = pathFile;
		this.file = new FileDatas(pathFile);
	}
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 * @param codeDatas le code de recuperation de donnees
	 */
	public AbstractParserFile(String pathFile, int codeDatas){
		this.pathFile = pathFile;
		this.file = new FileDatas(pathFile);
		this.codeDatas = codeDatas;
	}
	
	/**
	 * methode permettant de parser le fichier
	 */
	public void toParse(){
		
		file.openFile();
		numDataParsing = 0;
		this.update();
		
		if (file.isOpen()){
			String data  = getNextDatasFile();
			while (data != null){
				numDataParsing ++;
				this.update();
				executeAction(data);
				data = getNextDatasFile();
			}
		}
		
		file.closeFile();
		this.update();
	}
	
	/**
	 * methode permettant de parser le fichier en donnant le nombre de données suivantes à traiter
	 */
	public void toParse(int nbDatas){
		int nb = 0;
		
		if (! file.isOpen()){
			file.openFile();
			numDataParsing = 0;
			this.update();
		}
	
		if (file.isOpen() && nb < nbDatas){
			String data  = getNextDatasFile(); 
			while (data != null && nb < nbDatas){
				numDataParsing ++;
				this.update();
				executeAction(data);
				nb++;
				if (nb < nbDatas){
					data = getNextDatasFile();
				}
			}
			if (data == null){
				file.closeFile();
				this.update();
			}
		}
	}
	
	/**
	 * methode permettant de recuperer les prochaines donnees
	 * @return
	 */
	private String getNextDatasFile(){
		switch (codeDatas){
		case DATAS_WORD :
			return file.nextWord();
		case DATAS_LINE :
			return file.nextLine();
		case DATAS_ORIGINAL_LINE :
			return file.nextOriginalLine();
		case DATAS_BLOC :
			return file.nextBloc();
		case DATAS_ORIGINAL_BLOC :
			return file.nextOriginalBloc();
		default : 
			System.out.println("ERROR CODE DATAS");
		}
		return null;
	}
	
	/**
	 * methode abstraite permettant d'excecuter une action pour une ligne lu
	 * @param line la ligne lu
	 */
	public abstract void executeAction(String line);
	
	/**
	 * methode permettant de changer le separateur de bloc
	 * @param separatorBloc le separateur de bloc
	 */
	public void setSeparatorBloc(String separatorBloc){
		this.file = new FileDatas(this.pathFile, separatorBloc);
	}
	
	/**
	 * methode permettant d'obtenir le numero de la données courrante
	 * @return le numero de la donnée courrante
	 */
	public int getNumDataParsing(){
		return this.numDataParsing;
	}
	
	/**
	 * méthode permettant de savoir le status de l'analyse
	 * @return le status de l'analyse
	 */
	private String getStatus(){
		String typeDatas = this.getTypeDatas();
		if (file.isOpen()){
			if (this.numDataParsing == 0){
				return "Début";
			} else {
				return typeDatas + " en cours d'analyse : " + this.numDataParsing;
			}
		}
		
		return "Fin";
	}
	
	/**
	 * methode permettant d'afficher un message
	 * @param message
	 */
	private void printMessage(String message){
		System.out.println("fichier (" + this.pathFile + ") -> " + message);
	}
	
	/**
	 * methode permettant d'afficher l'erreur de la donnée courrante
	 */
	public void printErrorData(){
		this.printMessage("Erreur : " + this.numDataParsing);
	}
	
	/**
	 * methode permettant d'afficher le status du parser
	 */
	public void printStatus(){
		this.printMessage(getStatus());
	}
	
	/**
	 * methode permettant d'obtenir le String correspondant au type de donnée
	 * @return le type de données
	 */
	private String getTypeDatas(){
		switch (codeDatas){
		case DATAS_WORD: 
			return "mot";
		case DATAS_LINE: 
		case DATAS_ORIGINAL_LINE: 
			return "ligne";
		case DATAS_BLOC:
		case DATAS_ORIGINAL_BLOC:
			return "bloc";
		}
		return "donnée";
	}
	
	/**
	 * methode permettant d'avertir les observers que le parser à été mis à jour
	 */
	public void update(){
		this.setChanged();
		this.notifyObservers();
	}
}
