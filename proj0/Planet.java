public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G=6.67e-11;
    public Planet(double xP, double yP, double xV,double yV, double m, String img) {
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p) {
        this(p.xxPos, p.yyVel, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }
    public double calcDistance(Planet a) {
        double dx = this.xxPos-a.xxPos;
        double dy = this.yyPos-a.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }
    public double calcForceExertedBy(Planet a) {
        return G * this.mass * a.mass/(this.calcDistance(a)*this.calcDistance(a));
    }
    public double calcForceExertedByX(Planet a) {
        double force = this.calcForceExertedBy(a);
        double distance = this.calcDistance(a);
        return force*(a.xxPos-this.xxPos)/distance;
    }
    public double calcForceExertedByY(Planet a) {
        double force = this.calcForceExertedBy(a);
        double distance = this.calcDistance(a);
        return force*(a.yyPos-this.yyPos)/distance;
    }
    public double calcNetForceExertedByX(Planet[] allPlanet) {
        double allforceX =0;
        for (Planet a : allPlanet) {
            if (this.equals(a)) {
                continue;
            }
            allforceX=allforceX+this.calcForceExertedByX(a);
        }
        return allforceX;
    }
    public double calcNetForceExertedByY(Planet[] allPlanet) {
        double allforceY=0;
        for (Planet a : allPlanet) {
            if (this.equals(a)) {
               continue;
            }
            allforceY=allforceY+this.calcForceExertedByY(a);
        }
        return allforceY;
    }
    public void update(double dt,double fX,double fY) {
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel=this.xxVel+dt*ax;
        this.yyVel=this.yyVel+dt*ay;
        this.xxPos=this.xxPos+dt*this.xxVel;
        this.yyPos=this.yyPos+dt*this.yyVel;
        }
    public void draw() {
        String filepath = "images/"+imgFileName;
        StdDraw.picture(xxPos,yyPos,filepath);
    }
}