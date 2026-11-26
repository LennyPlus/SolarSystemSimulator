/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author 2551365
 */
public class SimulationView {
    private static final double MIN_CLICK_RADIUS = 6; // Pixels

    private Canvas canvas;
    private GraphicsContext gc;
    private Simulation simulation;
    private Body selectedBody;
    private static final double DISTANCE_SCALE = 100; // Pixels per AU
    private static final double PLANET_SCALE_FACTOR = 100000;
    
    // Used for camera translation
    private double cameraX = 0;
    private double cameraY = 0;
    
    private double zoomX = 1;
    private double zoomY = 1;
            
    
    public SimulationView(Canvas canvas, Simulation simulation) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.simulation = simulation;
    }
    
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        
        // Saves the currnt transform
        gc.save();
        // Center the canvas and scales everything
        gc.translate(screenOriginX(), screenOriginY());
        gc.scale(zoomX, zoomY);
        
        for (Body body : simulation.getBodies()) {
            Point2D center = scaledPosition(body);
            double radius = Math.max(drawRadius(body), 2 / Math.min(zoomX, zoomY));
            gc.setFill(body.getColor());
            gc.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
        }

        // Restores the saved transform
        gc.restore();

        // Draw names after restoring the transform so the text stays the same size.
        for (Body body : simulation.getBodies()) {
            Point2D center = scaledPosition(body);
            double x = screenOriginX() + center.getX() * zoomX;
            double y = screenOriginY() + center.getY() * zoomY;
            if (body == selectedBody) {
                double radiusX = Math.max(drawRadius(body) * zoomX, 6) + 4;
                double radiusY = Math.max(drawRadius(body) * zoomY, 6) + 4;
                gc.setStroke(Color.LIGHTBLUE);
                gc.setLineWidth(2);
                gc.strokeOval(x - radiusX, y - radiusY, radiusX * 2, radiusY * 2);
                gc.setFill(Color.LIGHTBLUE);
            } else {
                gc.setFill(Color.WHITE);
            }
            gc.fillText(body.getName(), x + drawRadius(body) * zoomX + 6, y - 8);
        }
    }

    Body findPlanetAt(double canvasX, double canvasY) {
        return findPlanetAt(simulation.getBodies(), new Point2D(canvasX, canvasY),
                new Point2D(screenOriginX(), screenOriginY()), zoomX, zoomY);
    }

    static Body findPlanetAt(List<Body> bodies, Point2D click, Point2D origin, double zoomX, double zoomY) {
        Body closest = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (Body body : bodies) {
            if (body.getDescription() == null) {
                continue;
            }

            Point2D center = scaledPosition(body);
            double dx = click.getX() - (origin.getX() + center.getX() * zoomX);
            double dy = click.getY() - (origin.getY() + center.getY() * zoomY);
            double radius = drawRadius(body);
            double radiusX = Math.max(radius * zoomX, MIN_CLICK_RADIUS);
            double radiusY = Math.max(radius * zoomY, MIN_CLICK_RADIUS);
            double distance = Math.hypot(dx, dy);
            if (Math.hypot(dx / radiusX, dy / radiusY) <= 1 && distance <= closestDistance) {
                closest = body;
                closestDistance = distance;
            }
        }
        return closest;
    }

    private double screenOriginX() {
        return canvas.getWidth() / 2 + cameraX;
    }

    private double screenOriginY() {
        return canvas.getHeight() / 2 + cameraY;
    }

    private static Point2D scaledPosition(Body body) {
        return new Point2D(body.getPosition().getX() * DISTANCE_SCALE, body.getPosition().getY() * DISTANCE_SCALE);
    }

    private static double drawRadius(Body body) {
        return Math.min(Math.max(body.getRadius() * PLANET_SCALE_FACTOR, 0.5), 5);
    }

    public void showFullSystem() {
        double minX = 0;
        double maxX = 0;
        double minY = 0;
        double maxY = 0;
        for (Body body : simulation.getBodies()) {
            Point2D point = scaledPosition(body);
            minX = Math.min(minX, point.getX());
            maxX = Math.max(maxX, point.getX());
            minY = Math.min(minY, point.getY());
            maxY = Math.max(maxY, point.getY());
        }

        // Leave room on the left for the facts panel and around the body labels.
        double widthScale = (canvas.getWidth() - 420) / Math.max(maxX - minX, 1);
        double heightScale = (canvas.getHeight() - 120) / Math.max(maxY - minY, 1);
        zoomX = Math.min(widthScale, heightScale);
        zoomY = zoomX;
        cameraX = 140 - (minX + maxX) / 2 * zoomX;
        cameraY = -(minY + maxY) / 2 * zoomY;
    }

    public void setSelectedBody(Body selectedBody) {
        this.selectedBody = selectedBody;
    }

    public void setCameraX(double cameraX) {
        this.cameraX = cameraX;
    }

    public void setCameraY(double cameraY) {
        this.cameraY = cameraY;
    }

    public void setZoomX(double zoomX) {
        this.zoomX = zoomX;
    }

    public void setZoomY(double zoomY) {
        this.zoomY = zoomY;
    }

    public double getZoomX() {
        return zoomX;
    }

    public double getZoomY() {
        return zoomY;
    }
    
    
    
    public void moveCameraBy(double x, double y) {
        setCameraX(cameraX + x);
        setCameraY(cameraY + y);
    }
    
    
}
