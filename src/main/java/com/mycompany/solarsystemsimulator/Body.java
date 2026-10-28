/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solarsystemsimulator;

/**
 *
 * @author arin.kbr
 */
public class Body {
    private String name;
    private double mass; // Kilograms (kg).
    private double radius; // Kilometres (km).
    private double distanceFromSun; // Kilometres (km), measured from the Sun's centre.
    private String description;

    public Body(String name, double mass, double radius,
            double distanceFromSun, String description) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.distanceFromSun = distanceFromSun;
        this.description = description;
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

    public double getDistanceFromSun() {
        return distanceFromSun;
    }

    public String getDescription() {
        return description;
    }
}
