/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author 2551365
 */
public class SimulationView extends Canvas{
    private GraphicsContext gc = getGraphicsContext2D();
    private Simulation simulation;

    public SimulationView(Simulation simulation, double d, double d1) {
        super(d, d1);
        this.simulation = simulation;
    }
    
    public void render() {
        for (int i = 0; i < simulation.getBodies().size(); i++) {
            Body body = simulation.getBodies().get(i);
            gc.fillOval(10, 10, body.getPosition().getX(), body.getPosition().getY());
        }
    }
    
}
