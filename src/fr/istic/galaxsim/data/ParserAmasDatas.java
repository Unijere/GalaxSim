package fr.istic.galaxsim.data;

/**
 * classe qui permet d'analyser et de recuperer les données d'amas
 * @author anaofind
 *
 */
public class ParserAmasDatas extends ParserCosmosDatas{

	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 */
	public ParserAmasDatas(String pathFile) {
		super(pathFile, 9);
	}
	
	/**
	 * methode permettant de verifier que la donnee est correcte
	 * @param data la donnee
	 */
	@Override
	public boolean isCorrectData(String data, int indice) {
		switch (indice){
		case 6 : 
			if (CheckData.isInteger(data)){
				int i = Integer.parseInt(data);
				return (i >= 100001 && i <= 400002) || i == 0;
			}
			return false;
			
		case 27 :
			if (CheckData.isDouble(data)){
				double d = Double.parseDouble(data);
				return (d >= 0 && d <= 494.3);
			}
			return false;
			
		case 33 : 
			return true;
			
		case 62 : 
			return CheckData.isDouble(data);
			
		case 69 :
			return CheckData.isDouble(data);
			
		case 78 :
			return CheckData.isDouble(data);
			
		case 87 : 
			return CheckData.isDouble(data);
			
		case 125 : 
			if (CheckData.isDouble(data)){
				double d = Double.parseDouble(data);
				return (d >= -189 && d <= 29883);
			}
			return false;
			
		case 168 : 
			if (CheckData.isDouble(data)){
				double d = Double.parseDouble(data);
				return (d >= 0 && d <= 24401);
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
		return (indice == 6 || indice == 27 || indice == 33 || indice == 62 || indice == 69 || indice == 78
				|| indice == 87 || indice == 125 || indice == 168) ;
	}

	/**
	 * methode permettant d'obtenir l'indice de la donnée dans le tableau final
	 * @param indice l'indice de la donnée dans le fichier
	 * @return l'indice de la donnée dans le tableau final
	 */
	@Override
	public int getFinalIndiceData(int indice) {
		switch (indice){
		case 6 : 
			return 0;
		case 27 :
			return 2;
		case 33 : 
			return 1;
		case 62 : 
			return 3;
		case 69 :
			return 4;
		case 78 :
			return 5;
		case 87 : 
			return 6 ;
		case 125 : 
			return 7 ;
		case 168 : 
			return 8;
		}
		return -1;
	}


}
