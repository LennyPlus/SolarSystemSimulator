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
    private double scale = 1; //10 AU = 1 pixel
    private double planetScaleFactor = 10000;

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
            System.out.println(body.getRadius());
            gc.setFill(Color.WHITE);
            gc.fillOval(body.getPosition().getX(), body.getPosition().getY(), body.getRadius() * planetScaleFactor, body.getRadius() * planetScaleFactor);
        }
        
        gc.scale(scale, scale);
        // Restores the saved transform
        gc.restore();
    }
    
}
