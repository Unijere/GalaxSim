package fr.istic.galaxsim.data;

/**
 * classe permettant de d�finir une galaxie
 * @author anaofind
 *
 */
public class Galaxy extends CosmosElement{

	/**
	 * l'identifiant de l'amas de la galaxie
	 */
	private int amasIdent;
	
	/**
	 * le nom de la galaxie
	 */
	private String name;
	
	/**
	 * le type de la galaxie
	 */
	private String type;
	
	/**
	 * la masse de l'amas associ�
	 */
	private double amasMass;
	
	/**
	 * constructeur
	 * @param ident l'identifiant
	 * @param distance la distance
	 * @param velocity la vitesse
	 * @param amasMass la masse de l'amas associ�
	 */
	public Galaxy(int ident, double distance, double velocity, String type, double amasMass) {
		super(ident, distance, velocity);
		this.type = type;
		this.amasMass = amasMass;
	}

	/**
	 * getter identifiant de l'amas
	 * @return l'identifiant de l'amas
	 */
	public int getAmasIdent() {
		return amasIdent;
	}

	/**
	 * setter identifiant de l'amas
	 * @param l'identifiant de l'amas
	 */
	public void setAmasIdent(int amasIdent) {
		this.amasIdent = amasIdent;
	}

	/**
	 * getter nom
	 * @return le nom de la galaxie
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter nom
	 * @param name le nom de la galaxie
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter type
	 * @return le type de la galaxie
	 */
	public String getType() {
		return type;
	}

	/**
	 * getter amasMass
	 * @return amasMass
	 */
	public double getAmasMass() {
		return amasMass;
	}

	/**
	 * methode permettant de creer une galaxy � partir d'un taleau de donn�es
	 * @param datas le tableau de donn�ees
	 * @return la nouvelle galaxy
	 */
	public static Galaxy create(String[] datas){
		if (datas.length == 11){
			String stringId = datas[0];
			String name = datas[1];
			String type = datas[2];
			String stringIdAmas = datas[3];
			String stringDist = datas[4];
			String stringGLon = datas[5];
			String stringGLat = datas[6];
			String stringSGLon = datas[7];
			String stringSGLat = datas[8];
			String stringVelo = datas[9];
			String stringMassAmas = datas[10];
			
			int id = -1;
			double dist = -1;
			double velo = -1;
			int idAmas = -1;
			double massAmas = 0;
			
			if (stringId != null) {
				id = Integer.parseInt(stringId);
			}
			if (stringDist != null){
				dist = Double.parseDouble(stringDist);
			}
			if (stringVelo != null){
				velo = Double.parseDouble(stringVelo);
			}
			if (stringIdAmas != null){
				idAmas = Integer.parseInt(stringIdAmas);
			}
			if (stringMassAmas != null){
				massAmas = Double.parseDouble(stringMassAmas);
			}
			
			Galaxy newGalaxy = new Galaxy(id, dist, velo, type, massAmas);
			
			newGalaxy.setName(name);
			newGalaxy.setAmasIdent(idAmas);
			
			if (stringGLon != null && stringGLat != null){
				double GLon = Double.parseDouble(stringGLon);
				double GLat = Double.parseDouble(stringGLat);
				newGalaxy.setGalacticLongLat(GLon, GLat);
			}
			
			if (stringSGLon != null && stringSGLat != null){
				double SGLon = Double.parseDouble(stringSGLon);
				double SGLat = Double.parseDouble(stringSGLat);
				newGalaxy.setSuperGalacticLongLat(SGLon, SGLat);
			}
			
			return newGalaxy;
		}
		
		return null;
	}
	
	/**
	 * methode toString
	 */
	public String toString(){
		return super.toString() + " | name: " + name + " | " + 
				"type: " + type + " | " + 
				"amasIdent: " + amasIdent + " | " + 
				"amasMass: " + amasMass;
	}
}
