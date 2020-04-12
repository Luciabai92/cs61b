public class NBody{

	public static double readRadius(String planetsTxtPath) {
		/** read in planet data from ./data/planets.txt */
		In in = new In(planetsTxtPath);
		in.readInt();
		/** read the radius of the universe */
		double R = in.readDouble();	
		return R;			
	}

	public static Body[] readBodies(String planetsTxtPath) {
		/** read in Body data from ./data/planets.txt */
		In in = new In(planetsTxtPath);
		/** read in num of Body */
		int num=0;
		num = in.readInt();
		in.readDouble();
		Body[] planets = new Body[num];

		for(int i=0; i<=num-1; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			planets[i] = new Body(xP, yP, xV, yV, m, img);
		}
		return planets;			
	}

	/** Drawing the Initial Universe State */
	public static void main (String[] args){
		/** collecting all needed input */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] planets = readBodies(filename);

		/** Sets up the universe so it goes from
		  * -radius, -radius up to radius, radius */
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

		/* Stamps three copies of advice.png in a triangular pattern. */
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/** Drawing More Than One Body */
		for (Body planet:planets){
			planet.draw();
		}
		/** drawing the background */
		StdDraw.enableDoubleBuffering();

		/** loop time */
		for(double time=0; time<=T; time+=dt){
			double[] xForces= new double[planets.length];
			double[] yForces= new double[planets.length];

			/** calculate the net forces for every planet*/
			for(int i=0; i<planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				
				/** update positions of each planet */
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			/** draw background */
			StdDraw.picture(0, 0, "images/starfield.jpg");

			/** Drawing More Than One Body */
			for (Body planet:planets){
				planet.draw();
			}

			/* Shows the drawing to the screen*/
			StdDraw.show();
			StdDraw.pause(10);
		}

		/** printing the universe */
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}	
	}

}

