package fr.istic.galaxsim.data;

import java.util.*;
/**
 * classe de parser de données de galaxies
 * @author anaofind
 *
 */
public abstract class ParserCosmosDatas extends AbstractParserFile{
	
	/**
	 * les donnees
	 */
	private ArrayList<String[]> allDatas = new ArrayList<String[]>();
	
	/**
	 * la taille maximale d'une donnee
	 */
	private int sizeMaxDatas = 0;
	
	/**
	 * la taille minimale d'une données
	 */
	private int sizeMinDatas = 100;
	
	/**
	 * constructeurs
	 * @param pathFile le chemin du fichier
	 */
	public ParserCosmosDatas(String pathFile) {
		super(pathFile);
	}
	
	/**
	 * constructeurs
	 * @param pathFile le chemin du fichier
	 * @param sizeMin la taille min des données
	 * @param sizeMax la taille max des données
	 */
	public ParserCosmosDatas(String pathFile, int sizeMin, int sizeMax) {
		super(pathFile);
		this.setSizeDatas(sizeMin, sizeMax);
	}
	
	/**
	 * constructeurs
	 * @param pathFile le chemin du fichier
	 * @param le code de donnees
	 */
	public ParserCosmosDatas(String pathFile, int codeDatas) {
		super(pathFile, codeDatas);
	}
	
	/**
	 * constructeurs
	 * @param pathFile le chemin du fichier
	 * @param le code de donnees
	 * @param sizeMin la taille min des données
	 * @param sizeMax la taille max des données
	 */
	public ParserCosmosDatas(String pathFile, int codeDatas, int sizeMin, int sizeMax) {
		super(pathFile, codeDatas);
		this.setSizeDatas(sizeMin, sizeMax);
	}

	@Override
	public void executeAction(String datas) {
		String[] arrayDatas = datas.split(" ");
		
		if (arrayDatas.length <= this.sizeMaxDatas && arrayDatas.length >= this.sizeMinDatas){
			String[] importantDatas = this.extractImportantDatas(arrayDatas); 
			if (importantDatas != null && importantDatas.length > 0){
				this.allDatas.add(importantDatas);
			}
		}
	}
	
	
	
	/**
	 * methode qui retourne les donnees sous forme de tableau
	 * @return les donnees 
	 */
	public String[][] getAllDatas(){
		if (this.allDatas.size() > 0){
			int size = this.allDatas.size();
			String[][] d = new String[size][sizeMaxDatas];
			for (int i = 0; i<size; i++){
				d[i] = this.allDatas.get(i);
			}
			return d;
		}
		return null;
	}
	
	/**
	 * methode permettant d'extraire que ce qui est important des données
	 * @param datas les données
	 * @return ce qui est important des données
	 */
	private String[] extractImportantDatas(String[] datas){
		ArrayList<String> listDatas = new ArrayList<String>();
		for (int i = 0; i<datas.length; i++){
			String d = datas[i];
			if (this.isCorrectData(d, i)){
				if (this.isImportantData(d, i)){
					listDatas.add(d);
				}
			} else {
				return null;
			}
		}
		return listDatas.toArray(new String[listDatas.size()]);
	}
	
	/**
	 * methode permettant de verifier que la donnee est correcte
	 * @param data la donnee
	 * @param l'indice de la donnée
	 */
	public abstract boolean isCorrectData(String data, int indice);
	
	/**
	 * methode permettant de savoir si la donnee est interressante
	 * @param data la donnee
	 * @param l'indice de la donnée
	 * @return boolean indiquant si la donnee est interressante
	 */
	public abstract boolean isImportantData(String data, int indice);
	
	/**
	 * methode permettant de modifier la taille min et max des données
	 * @param min la taille min 
	 * @param max la taille max
	 */
	public void setSizeDatas(int min, int max){
		this.sizeMinDatas = min;
		this.sizeMaxDatas = max;
	}
}
