public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numplanets = in.readInt();
        double r = in.readDouble();
        return r;
    }
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numplanets = in.readInt();
        in.readDouble();
        Planet[] Planets = new Planet[numplanets];
        int i = 0;
        while (i<numplanets) {
           double xxPos = in.readDouble();
           double yyPos = in.readDouble();
           double xxVel = in.readDouble();
           double yyVel = in.readDouble();
           double mass = in.readDouble();
           String imgFilename = in.readString();
           Planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFilename);
           i=i+1;
        }
        return Planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius = NBody.readRadius(filename);
        Planet[] Planets = NBody.readPlanets(filename);

        StdDraw.setScale(-Radius, Radius);
        StdDraw.clear();
        String background = "images/starfield.jpg";
        StdDraw.picture(0,0,background);

        for (Planet a : Planets) {
            a.draw();
        }

    }
}

