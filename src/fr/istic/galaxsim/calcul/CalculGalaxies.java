package fr.istic.galaxsim.calcul;
import fr.istic.galaxsim.data.Amas;
import fr.istic.galaxsim.data.Coordinate;
import fr.istic.galaxsim.data.Galaxy;


public class CalculGalaxies {
	/**
	*
	*	Classe permettant de réaliser les calculs de position
	* pour les galaxies et amas de galaxies
	*
	*/

			// calcule les coordonnées initiales d'une galaxie
		  public static void calculCoordInit(Galaxy g1)
		  {
				//variables coordonnées
				double x;
				double y;
				double z;

				//calcul des coordonnées
			 	z = Math.sin(Math.toRadians(g1.getSuperGalacticLat())) * g1.getDistance();
			  double hypothenus = Math.cos(Math.toRadians(g1.getSuperGalacticLat())) * g1.getDistance();
			 	x = Math.cos(Math.toRadians(g1.getSuperGalacticLon())) * hypothenus;
			  y = Math.sin(Math.toRadians(g1.getSuperGalacticLon())) * hypothenus;

				//enregistrement des données initiales
				Coordinate coord = new Coordinate(x, y, z);
				g1.addCoordinate(coord);
		  }

			// calcule la force d'attraction entre deux galaxies
		  public static double forceAttraction(Galaxy g1, Amas a, int t)
		  {

		    // distance = racine carre de ((x1 + x2)^2 + (y1 + y2)^2 + (z1 + z2)^2)
		    double x = Math.pow(g1.getCoordinate(t).getX() - a.getCoordinate(t).getX(), 2);
		    double y = Math.pow(g1.getCoordinate(t).getY() - a.getCoordinate(t).getY(), 2);
		    double z = Math.pow(g1.getCoordinate(t).getZ() - a.getCoordinate(t).getZ(), 2);
		    double distance = Math.sqrt(x + y + z);

		    // Force de gravitation = (G * Masse1 * Masse2) / distance^2
		    distance = distance * 3.085677581 * Math.pow(10, 22);
		    distance = Math.pow(distance, 2);
		    double G = 6.67408 * Math.pow(10, -11);
		    double masse = a.getMass() * 1.991 * Math.pow(10, 42);
		    double F = (G * masse) / distance;

		    return F;
		  }

			//calcule la longitude entre deux galaxies
			public static double longitudeAttraction(Galaxy g1, Amas a, int t)
		 	{
				double x = a.getCoordinate(t).getX() - g1.getCoordinate(t).getX();
				double y = a.getCoordinate(t).getY() - g1.getCoordinate(t).getY();

				return Math.atan(x/y);
		 	}

			//calcule la latitude entre deux galaxies
			public static double latitudeAttraction(Galaxy g1, Amas a, int t)
		 	{
				double x = Math.pow(a.getCoordinate(t).getX() - g1.getCoordinate(t).getX(), 2);
		    double y = Math.pow(a.getCoordinate(t).getY() - g1.getCoordinate(t).getY(), 2);
				double z = a.getCoordinate(t).getZ() - g1.getCoordinate(t).getZ();
				double hypothenus = Math.sqrt(x + y);

				return Math.atan(z/hypothenus);
		 	}

			//retourne la force d'attraction entre deux galaxies sur l'axe X
			public static double forceX(Galaxy g1, Amas a, int t, double F)
			{
				double longitude = longitudeAttraction(g1, a, t);
				
				return F * Math.cos(longitude);
			}

			//retourne la force d'attraction entre deux galaxies sur l'axe Y
			public static double forceY(Galaxy g1, Amas a, int t, double F)
			{
				double longitude = longitudeAttraction(g1, a, t);

				return  F * Math.sin(longitude);
			}

			//retourne la force d'attraction entre deux galaxies sur l'axe Z
			public static double forceZ(Galaxy g1, Amas a, int t, double F)
			{
				double latitude = latitudeAttraction(g1, a, t);

				return F * Math.sin(latitude);
			
			}

			//retourne la vitesse de la galaxie sur l'axe X
			public static double vitesseX(Galaxy g1, double sommeForceX, double sommeForceY)
			{
				double angle;
				double vitesse = 71 * g1.getDistance();
				vitesse = g1.getVelocity() - vitesse;

				if( (sommeForceX > 0 && sommeForceY >= 0) || (sommeForceX < 0 && sommeForceY <= 0) )
				{
					angle = Math.atan(sommeForceY/sommeForceX);
					return vitesse* Math.cos(angle);
				}
				else
				{
					angle = Math.atan(sommeForceX/sommeForceY);
					return vitesse* Math.cos(angle);
				}
			
			}

			//retourne la vitesse de la galaxie sur l'axe Y
			public static double vitesseY(Galaxy g1, double sommeForceX, double sommeForceY)
			{
				double angle;
				double vitesse = 71 * g1.getDistance();
				vitesse = g1.getVelocity() - vitesse;

				if( (sommeForceX > 0 && sommeForceY >= 0) || (sommeForceX < 0 && sommeForceY <= 0) )
				{
					angle = Math.atan(sommeForceY/sommeForceX);
					return vitesse* Math.sin(angle);
				}
				else
				{
					angle = Math.atan(sommeForceX/sommeForceY);
					return vitesse* Math.sin(angle);
				}

			}

			//retourne la vitesse de la galaxie sur l'axe Z
			public static double vitesseZ(Galaxy g1, double sommeForceX, double sommeForceY, double sommeForceZ)
			{
				double angle;
				double vitesse = 71 * g1.getDistance();
				vitesse = g1.getVelocity() - vitesse;

				double x = Math.pow(sommeForceX,2);
				double y = Math.pow(sommeForceY,2);

				double Zprim = Math.sqrt(x+y);

				if( (Zprim > 0 && sommeForceZ >= 0) || (Zprim < 0 && sommeForceZ <= 0) )
				{
					angle = Math.atan(sommeForceZ/Zprim);
					return vitesse* Math.sin(angle);
				}
				else
				{
					angle = Math.atan(Zprim/sommeForceZ);
					return vitesse* Math.cos(angle);
				}
			}

			public static void coordByTime(Galaxy g1, double sommeForceX, double sommeForceY, double sommeForceZ, int t)
			{
				double Ax = sommeForceX;
				double Ay = sommeForceY;
				double Az = sommeForceZ;

				double Vx = vitesseX(g1, sommeForceX, sommeForceY);
				double Vy = vitesseY(g1, sommeForceX, sommeForceY);
				double Vz = vitesseZ(g1, sommeForceX, sommeForceY, sommeForceZ);

				double time = t * Math.pow(10, 15);

				double x = (Ax * time * time)/2 + time*Vx + g1.getCoordinate(t).getX();
				double y = (Ay * time * time)/2 + time*Vy + g1.getCoordinate(t).getY();
				double z = (Az * time * time)/2 + time*Vz + g1.getCoordinate(t).getZ();

				Coordinate coord = new Coordinate(x, y, z);
				g1.addCoordinate(coord);
			}
		}
