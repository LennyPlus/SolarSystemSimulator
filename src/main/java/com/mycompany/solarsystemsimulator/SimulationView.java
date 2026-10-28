/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author 2551365
 */
public class SimulationView {
    private Canvas canvas;
    private GraphicsContext gc;
    private Simulation simulation;
    private double distanceScale = 20; //50 pixels = 1 AU
    private double planetScaleFactor = 1000;

    public SimulationView(Canvas canvas, Simulation simulation) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.simulation = simulation;
    }
    
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        
        // Saves the currnt transform
        gc.save();
        // Center the canvas and scales everything
        gc.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        
        
        for (int i = 0; i < simulation.getBodies().size(); i++) {
            Body body = simulation.getBodies().get(i);
            
            double x = body.getPosition().getX() * distanceScale;
            double y = body.getPosition().getY() * distanceScale;

            double radius = Math.clamp(body.getRadius() * planetScaleFactor, 0.5, 5.0);

            gc.setFill(body.getColor());
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
            
        }
        
        // Restores the saved transform
        gc.restore();
    }
    
}
