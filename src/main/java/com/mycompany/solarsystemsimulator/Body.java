/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

/**
 *
 * @author arin.kbr
 */
public class Body {
    private String name;
    private double mass; // Kilograms (kg).
    private double radius; // Kilometres (km).
    private Color color;
    private String description;
    
    // Motion vectors
    private Point3D position;
    private Point3D velocity;
    private Point3D acceleration;

    public Body(String name, double mass, double radius, Color color, Point3D position, Point3D velocity) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.color = color;
        this.position = position;
        this.velocity = velocity;
    }

    public Body(String name, double mass, double radius, String description) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.description = description;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public void setVelocity(Point3D velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Point3D acceleration) {
        this.acceleration = acceleration;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }
    
    public String getDescription() {
        return description;
    }

    public Point3D getPosition() {
        return position;
    }

    public Point3D getVelocity() {
        return velocity;
    }

    public Point3D getAcceleration() {
        return acceleration;
    }
}
