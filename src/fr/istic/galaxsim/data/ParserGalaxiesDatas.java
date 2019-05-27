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
		super(pathFile, 10);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * methode permettant de verifier que la donnee est correcte
	 * @param data la donnee
	 */
	@Override
	public boolean isCorrectData(String data, int indice) {
		switch (indice){
		case 7 : 
			if (CheckData.isInteger(data)){
				int i = Integer.parseInt(data);
				return (i >= 4 && i <= 9003164);
			}
			return false;
			
		case 14 :
			if (CheckData.isDouble(data)){
				double d = Double.parseDouble(data);
				return (d >= 0.05 && d <= 517.7);
			}
			return false;
			
		case 120 : 
			return CheckData.isDouble(data);
			
		case 129 : 
			return CheckData.isDouble(data);
			
		case 138 :
			return CheckData.isDouble(data);
			
		case 147 :
			return CheckData.isDouble(data);
			
		case 152 : 
			if (CheckData.isDouble(data)){
				double d = Double.parseDouble(data);
				return (d >= -5 && d <= 10);
			}
			return false;

		case 182 : 
			if (CheckData.isDouble(data)){
				double d = Double.parseDouble(data);
				return (d >= -339 && d <= 34591);
			}
			return false;
			
		case 211 : 
			return true;
			
		case 218 : 
			if (CheckData.isInteger(data)){
				int i = Integer.parseInt(data);
				return (i >= 100001 && i <= 400002) || i == 0;
			}
			return false;
		}
		
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
		return (indice == 7 || indice == 14 || indice == 120 || indice == 129 || indice == 138 || indice == 147
				|| indice == 152 || indice == 182 || indice == 211 || indice == 218) ;
	}

	@Override
	public int getFinalIndiceData(int indice) {
		switch (indice){
		case 7 : 
			return 0;
		case 14 : 
			return 4;
		case 120 :
			return 5;
		case 129 : 
			return 6;
		case 138 :
			return 7;
		case 147 :
			return 8;
		case 152 : 
			return 2 ;
		case 182 : 
			return 9 ;
		case 211 : 
			return 1;
		case 218 : 
			return 3;
		}
		return -1;
	}



}
