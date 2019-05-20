package fr.istic.galaxsim.data;

/**
 * classe permettant de définir un amas
 * @author anaofind
 *
 */
public class Amas extends CosmosElement{

	/**
	 * la masse en TMsun
	 */
	private double mass; 
	
	/**
	 * constructeur
	 * @param ident l'identifiant
	 * @param distance la distance
	 * @param velocity la vitesse
	 */
	public Amas(int ident, double distance, double velocity, double mass) {
		super(ident, distance, velocity);
		this.mass = mass;
	}

	/**
	 * getter masse 
	 * @return la masse
	 */
	public double getMass() {
		return mass;
	}
	
	/**
	 * methode permettant de creer un amas à partir d'un taleau de données
	 * @param datas le tableau de donnéees
	 * @return le nouvel amas
	 */
	public static Amas create(String datas[]){
		if (datas.length == 9){
			String stringId = datas[0];
			String stringDist = datas[2];
			String stringGLon = datas[3];
			String stringGLat = datas[4];
			String stringSGLon = datas[5];
			String stringSGLat = datas[6];
			String stringVelo = datas[7];
			String stringMass = datas[8];
			
			int id = -1;
			double dist = -1;
			double velo = -1;
			double mass = -1;
			
			if (stringId != null) {
				id = Integer.parseInt(stringId);
			}
			if (stringDist != null){
				dist = Double.parseDouble(stringDist);
			}
			if (stringVelo != null){
				velo = Double.parseDouble(stringVelo);
			}
			if (stringMass != null){
				mass = Double.parseDouble(stringMass);
			}
			
			Amas newAmas = new Amas(id, dist, velo, mass);
			
			if (stringGLon != null && stringGLat != null){
				double GLon = Double.parseDouble(stringGLon);
				double GLat = Double.parseDouble(stringGLat);
				newAmas.setGalacticLongLat(GLon, GLat);
			}
			
			if (stringSGLon != null && stringSGLat != null){
				double SGLon = Double.parseDouble(stringSGLon);
				double SGLat = Double.parseDouble(stringSGLat);
				newAmas.setSuperGalacticLongLat(SGLon, SGLat);
			}
			
			return newAmas;
		}
		
		return null;
	}
	
	/**
	 * methode toString
	 */
	public String toString(){
		return super.toString() + " | masse: " + mass;
	}
}
