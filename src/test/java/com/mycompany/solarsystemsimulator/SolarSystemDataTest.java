package com.mycompany.solarsystemsimulator;

import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class SolarSystemDataTest {
    @Test
    public void allEightPlanetsHaveDescriptions() {
        List<Body> bodies = SolarSystemData.newSimulationBodies();
        List<Body> planets = bodies.subList(1, bodies.size());

        assertEquals(List.of("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"),
                planets.stream().map(Body::getName).toList());
        for (Body planet : planets) {
            String description = planet.getDescription();
            assertFalse(planet.getName() + " needs a description", description == null || description.isBlank());
        }
    }

    @Test
    public void newRunRestoresStartingPositionsAndVelocities() {
        List<Body> reference = SolarSystemData.newSimulationBodies();
        List<Body> firstRun = SolarSystemData.newSimulationBodies();
        new Simulation(1, firstRun).update();
        assertNotEquals(reference.get(3).getPosition(), firstRun.get(3).getPosition());
        assertNotEquals(reference.get(3).getVelocity(), firstRun.get(3).getVelocity());

        List<Body> nextRun = SolarSystemData.newSimulationBodies();
        for (int index = 0; index < reference.size(); index++) {
            Body initial = reference.get(index);
            Body restarted = nextRun.get(index);
            assertNotSame(firstRun.get(index), restarted);
            assertEquals(initial.getName(), restarted.getName());
            assertEquals(initial.getPosition(), restarted.getPosition());
            assertEquals(initial.getVelocity(), restarted.getVelocity());
        }
    }
}
