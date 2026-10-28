/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import java.util.List;
import javafx.geometry.Point3D;

/**
 *
 * @author 2551365
 */
public class Simulation {
    private double timestep;
    private List<CelestialBody> bodies;
    private static double G = 6.674 * Math.pow(10, -11);
    
    
    public void update() {
        for (int i = 0; i < bodies.size(); i++) {
            for (int j = 0; j < bodies.size(); j++) {
                if (i != j) {
                    CelestialBody body1 = bodies.get(i);
                    CelestialBody body2 = bodies.get(j);
                    
                    Point3D force = gravitationalForce(body1, body2);
                    body1.setAcceleration(body1.getAcceleration().add(force.multiply(1 / body1.getMass())));
                }
            }
        }
        
        for (int i = 0; i < bodies.size(); i++) {
            CelestialBody body = bodies.get(i);
            
            body.setVelocity(body.getVelocity().add(body.getAcceleration().multiply(timestep)));
            body.setPosition(body.getPosition().add(body.getVelocity().multiply(timestep)));
        }
    }
    
    public Point3D gravitationalForce(CelestialBody body1, CelestialBody body2) {
        Point3D distance = body2.getPosition().subtract(body1.getPosition());
        return distance.normalize().multiply(-G * (body1.getMass() * body2.getMass()) / Math.pow(distance.magnitude(), 2));
    }
}
