public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Body(Body b){
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}


	public double calcDistance(Body b){
		double xDif=b.xxPos-xxPos;
		double yDif=b.yyPos-yyPos;
		return Math.pow(xDif*xDif+yDif*yDif,0.5);

	}

	public double calcForceExertedBy(Body b){
		double xDif=b.xxPos-xxPos;
		double yDif=b.yyPos-yyPos;
		return 6.67e-11*mass*b.mass/(xDif*xDif+yDif*yDif);
	}

	public double calcForceExertedByX(Body b){
		double xDif=b.xxPos-xxPos;

		return calcForceExertedBy(b)*xDif/calcDistance(b);
	}

	public double calcForceExertedByY(Body b){
		double yDif=b.yyPos-yyPos;

		return calcForceExertedBy(b)*yDif/calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double netf=0;
		for(Body b:allBodys){
			if(!this.equals(b))
			netf+=calcForceExertedByX(b);
		}
		return netf;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double netf=0;
		for(Body b:allBodys){
			if(!this.equals(b))
			netf+=calcForceExertedByY(b);
		}
		return netf;
	}

	public void update(double dt,double fX,double fY){
		double xxAcc=fX/mass;
		double yyAcc=fY/mass;

		xxVel+=xxAcc*dt;
		yyVel+=yyAcc*dt;
		xxPos+=xxVel*dt;
		yyPos+=yyVel*dt;
	}

	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}

}
