public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/** Calculate the distance between two Bodys */
	public double calcDistance (Body b){
		double dx = this.xxPos-b.xxPos;
		double dy = this.yyPos-b.yyPos;
		double r = Math.hypot(dx,dy);
		return r;
	}

	/** Calculate the force exerted on this body by the given body */
	public double calcForceExertedBy (Body b){
		double G = 6.67e-11;
		double F = (G * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);
        return F; 
	}

	/** Calculate the X force exerted on this body by the given body */
	public double calcForceExertedByX (Body b){
		double Fx = this.calcForceExertedBy(b)*(b.xxPos-this.xxPos)/this.calcDistance(b);
        return Fx; 
	}

	/** Calculate the Y force exerted on this body by the given body */
	public double calcForceExertedByY (Body b){
		double Fy = this.calcForceExertedBy(b)*(b.yyPos-this.yyPos)/this.calcDistance(b);
        return Fy; 
	}

	/** Calculate the net X force exerted by all bodies */
	public double calcNetForceExertedByX (Body[] allBodys){
		double Fnetx=0;
		for (int i=0; i<allBodys.length; i=i+1){
			if(this.equals(allBodys[i])){
				continue;
			}else{
				Fnetx += this.calcForceExertedByX(allBodys[i]);
			}
		}
		return Fnetx;
	}

	/** Calculate the net Y force exerted by all bodies */
	public double calcNetForceExertedByY (Body[] allBodys){
		double Fnety=0;
		for (int i=0; i<allBodys.length; i=i+1){
			if(this.equals(allBodys[i])){
				continue;
			}else{
				Fnety += this.calcForceExertedByY(allBodys[i]);
			}
		}
		return Fnety;
	}

	/** Update the velocity and position of the Body under the effect of force */
    public void update(double dt, double fX, double fY) {
        this.xxVel += fX / this.mass * dt;
        this.yyVel += fY / this.mass * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
    
    /** Drawing on Body */
    public void draw (){
    	StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }

}