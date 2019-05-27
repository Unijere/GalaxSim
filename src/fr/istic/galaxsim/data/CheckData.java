package fr.istic.galaxsim.data;

/**
 * classe permettant de faire toute sorte de v�rification sur une donn�e
 * @author anaofind
 *
 */
public class CheckData {

	/**
	 * methode permettant de savoir si une donn�e est un double
	 * @param data la donn�e
	 * @return boolean indiquant si la donn�e est un double ou non
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
	 * methode permettant de savoir si une donn�e est un entier
	 * @param data la donn�e
	 * @return boolean indiquant si la donn�e est un entier ou non
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
	 * methode permettant de savoir si une donn�e est de la bonne taille
	 * @param data la donn�e
	 * @param sizeMin la taille min
	 * @param sizeMax la taille max
	 * @return boolean indiquant si la donn�e est de bonne taille
	 */
	public static boolean isGoodSize(String data, int sizeMin, int sizeMax){
		int size = data.length();
		return (size >= sizeMin && size <= sizeMax);
	}
}
