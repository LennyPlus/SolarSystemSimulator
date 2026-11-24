package com.mycompany.solarsystemsimulator;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class SimulationViewTest {
    @Test
    public void clickFollowsCameraZoomAndPlanetMotion() {
        Body planet = planetAt("Test planet", 1);
        List<Body> bodies = List.of(planet);
        assertSame(planet, SimulationView.findPlanetAt(bodies, new Point2D(500, 300),
                new Point2D(400, 300), 1, 1));

        Point2D movedOrigin = new Point2D(430, 280);
        planet.setPosition(new Point3D(1, 0.5, 0));
        assertSame(planet, SimulationView.findPlanetAt(bodies, new Point2D(634, 430), movedOrigin, 2, 3));
        assertNull(SimulationView.findPlanetAt(bodies, new Point2D(500, 300), movedOrigin, 2, 3));
        assertNull(SimulationView.findPlanetAt(bodies, new Point2D(639, 430), movedOrigin, 2, 3));
    }

    @Test
    public void clickChoosesNearestPlanetAndSkipsSun() {
        Body sun = new Body("Sun", 1, 0.01, Color.YELLOW, Point3D.ZERO, Point3D.ZERO);
        Body first = planetAt("First", 1);
        Body second = planetAt("Second", 1.08);
        List<Body> bodies = List.of(sun, first, second);
        Point2D origin = new Point2D(400, 300);
        assertNull(SimulationView.findPlanetAt(bodies, origin, origin, 1, 1));
        assertSame(first, SimulationView.findPlanetAt(bodies, new Point2D(503, 300), origin, 1, 1));
        assertSame(second, SimulationView.findPlanetAt(bodies, new Point2D(505, 300), origin, 1, 1));
    }

    private Body planetAt(String name, double x) {
        return new Body(name, 0.00001, 0.00001, Color.BLUE,
                new Point3D(x, 0, 0), Point3D.ZERO, "Planet facts");
    }
}
