package fr.istic.galaxsim.data;

import java.util.Observable;
import java.util.Observer;

/**
 * classe permettant d'obtenir les infos en temps réel sur un parser
 * @author anaofind
 *
 */
public class InfoParser implements Observer{
	
	/**
	 * singleton de l'information des parsers
	 */
	public static InfoParser infoParser;

	/**
	 * constructeur privé
	 */
	private InfoParser(){
	}
	
	/**
	 * methode permettant d'ajouter un parser
	 * @param parser le parser à ajouter
	 */
	public static synchronized void addParser(AbstractParserFile parser){
		if (infoParser == null){
			infoParser = new InfoParser();
		}
		parser.addObserver(infoParser);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof AbstractParserFile){
			AbstractParserFile parser = (AbstractParserFile) o;
			parser.printStatus();
		}
	}
	
	
	
	
}
