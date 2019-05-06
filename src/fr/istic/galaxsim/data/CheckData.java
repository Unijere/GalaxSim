package fr.istic.galaxsim.data;

/**
 * classe permettant de faire toute sorte de vérification sur une donnée
 * @author anaofind
 *
 */
public class CheckData {

	/**
	 * methode permettant de savoir si une donnée est un double
	 * @param data la donnée
	 * @return boolean indiquant si la donnée est un double ou non
	 */
	public static boolean isDouble(String data){
		try { 
	        Double.parseDouble(data); 
	    } catch (NumberFormatException e) { 
	        return false; 
	    } catch (NullPointerException e) {
	        return false;
	    }

	    return true;
	}
	
	/**
	 * methode permettant de savoir si une donnée est un entier
	 * @param data la donnée
	 * @return boolean indiquant si la donnée est un entier ou non
	 */
	public static boolean isInteger(String data){
		try { 
	        Integer.parseInt(data); 
	    } catch (NumberFormatException e) { 
	        return false; 
	    } catch (NullPointerException e) {
	        return false;
	    }

	    return true;
	}
	
	/**
	 * methode permettant de savoir si une donnée est de la bonne taille
	 * @param data la donnée
	 * @param sizeMin la taille min
	 * @param sizeMax la taille max
	 * @return boolean indiquant si la donnée est de bonne taille
	 */
	public static boolean isGoodSize(String data, int sizeMin, int sizeMax){
		int size = data.length();
		return (size >= sizeMin && size <= sizeMax);
	}
}
