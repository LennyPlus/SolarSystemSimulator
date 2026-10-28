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

    public SimulationView(Canvas canvas, Simulation simulation) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.simulation = simulation;
    }
    
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // Saves the currnt transform
        gc.save();
        // Center the canvas
        gc.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        System.out.println(gc.getTransform());
        for (int i = 0; i < simulation.getBodies().size(); i++) {
            Body body = simulation.getBodies().get(i);
            gc.setFill(Color.BLACK);
            gc.fillOval(body.getPosition().getX(), body.getPosition().getY(), 10, 10 );
        }
        
        // Restores the saved transform
        gc.restore();
    }
    
}
