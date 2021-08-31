public class NBody{

	public static double readRadius(String s){
		In in=new In(s);
		in.readInt();

		return in.readDouble();
	}

	public static Body[] readBodies(String s){
		In in=new In(s);
		int N=in.readInt();
		in.readDouble();

		Body[] bodies=new Body[N];

		for(int i=0;i<N;i+=1){
			double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xP, yP, xV, yV, m, img);
		}
		return bodies;
	}

	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);

		String filename=args[2];
		double radius=readRadius(filename);
		Body[] bodies=readBodies(filename);
		int N=bodies.length;

		StdDraw.setScale(-radius,radius);
		

		StdDraw.enableDoubleBuffering();

		double t=0;
		while(t<T){
			double[] xForces=new double[N];
			double[] yForces=new double[N];

			for(int i=0;i<N;i++){
				xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
				yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
			}

			for(int i=0;i<N;i++){
				bodies[i].update(dt,xForces[i],yForces[i]);
			}

			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Body b:bodies){
				b.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			t+=dt;
		}

		StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
        	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
        }

	}
}














