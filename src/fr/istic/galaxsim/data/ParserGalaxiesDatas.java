package fr.istic.galaxsim.data;


/**
 * classe qui permet d'analyser et de recuperer les données de galaxies
 * @author anaofind
 *
 */
public class ParserGalaxiesDatas extends ParserCosmosDatas{

	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 */
	public ParserGalaxiesDatas(String pathFile) {
		super(pathFile);
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 * @param sizeMin la taille min des données
	 * @param sizeMax la taille max des données
	 */
	public ParserGalaxiesDatas(String pathFile, int sizeMin, int sizeMax) {
		super(pathFile, sizeMin, sizeMax);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * methode permettant de verifier que la donnee est correcte
	 * @param data la donnee
	 */
	@Override
	public boolean isCorrectData(String data, int indice) {
		// TODO Auto-generated method stub
		// à completer
		return true;
	}

	/**
	 * methode permettant de savoir si la donnee est interressante
	 * @param data la donnee
	 * @param l'indice de la donnée
	 * @return boolean indiquant si la donnee est interressante
	 */
	@Override
	public boolean isImportantData(String data, int indice) {
		// TODO Auto-generated method stub
		// à completer
		return true;
	}



}
