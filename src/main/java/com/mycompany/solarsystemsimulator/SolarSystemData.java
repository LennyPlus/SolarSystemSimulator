package com.mycompany.solarsystemsimulator;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

public class SolarSystemData {
    // Masses are in solar masses. Distances are in AU. Time is in days
    // THe data is generated relative to the Sun on 2026-10-28
    // Data obtained from the NASA Horizons api: https://ssd.jpl.nasa.gov/horizons/app.html#/
    // Masses are taken from wikipedia and converted to solar masses
    
//    public static final Body SUN = new Body("Sun", 1.9884e30, 695700.0, "The star at the centre of our solar system.");
//    public static final Body EARTH = new Body("Earth", 5.9722e24, 6371.0, "Our home planet, the third planet from the Sun.");
    
    public static final Body SUN = new Body("Sun", 1, kmToAU(695500), Color.YELLOW, Point3D.ZERO, Point3D.ZERO);
    
    public static final Body MERCURY = new Body("Mercury", 1.65055E-7, kmToAU(2439.4), Color.GRAY,
            new Point3D(3.524147667938602E-01, 6.621305725661851E-03, -3.178105286088669E-02),
            new Point3D(-5.962592463435759E-03, 2.938667583568012E-02, 2.948470215803164E-03));
    
    public static final Body VENUS = new Body("Venus", 0.0000024337, kmToAU(6051.8), Color.BEIGE,
            new Point3D(5.811505289676687E-01, 4.304661260502406E-01, -2.761630281953054E-02),
            new Point3D(-1.210067509638803E-02, 1.616688578979737E-02, 9.203640128548601E-04));
    
    public static final Body EARTH = new Body("Earth", 0.0000029861, kmToAU(6371), Color.BLUE,
            new Point3D(8.217896106478431E-01, 5.586924462436930E-01, -4.184891455262764E-05),
            new Point3D(-9.945752095816504E-03, 1.415903687034667E-02, -7.952403014471578E-07));
    
    public static final Body MARS = new Body("Mars", 3.20855E-7, kmToAU(3389.5), Color.ROSYBROWN,
            new Point3D(-2.352021638316075E-01, 1.571997544521290E+00, 3.871045960235844E-02),
            new Point3D(-1.330964221868380E-02, -8.823242552545569E-04, 3.078603636747692E-04));
    
    public static final Body JUPITER = new Body("Jupiter", 0.0009490625, kmToAU(69886), Color.ORANGE,
            new Point3D(-3.643858817382849E+00, 3.869622673542852E+00, 6.545204133720292E-02),
            new Point3D(-5.586032451617269E-03, -4.825359970632274E-03, 1.450368585991290E-04));
    
    public static final Body SATURN = new Body("Saturn", 0.0009490625, kmToAU(58232), Color.BISQUE,
            new Point3D(9.221381096669472E+00, 1.914046918740637E+00, -4.004026015314515E-01),
            new Point3D(-1.443758160331379E-03, 5.448117880473655E-03, -3.737486435801490E-05));
    
    public static final Body URANUS = new Body("Uranus", 0.0009490625, kmToAU(25362), Color.CYAN,
            new Point3D(8.835333962643675E+00, 1.731205155817433E+01, -5.026430549691411E-02),
            new Point3D(-3.538037196545882E-03, 1.602096142851125E-03, 5.189560992457683E-05));
    
    public static final Body NEPTUNE = new Body("Neptune", 0.0009490625, kmToAU(24622), Color.AQUA,
            new Point3D(2.983282788330961E+01, 1.465245680617243E+00, -7.176643755423113E-01),
            new Point3D(-1.803391324406298E-04, 3.150842160711341E-03, -6.096940839377589E-05));
    
    
    public static double kmToAU(double km) {
        return km * 6.684587E-9;
    }
}
