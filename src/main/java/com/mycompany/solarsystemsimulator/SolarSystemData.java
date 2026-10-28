package com.mycompany.solarsystemsimulator;

public class SolarSystemData {
    // Masses are in kg. Mean radii and distances are in km.
    // Source: https://nssdc.gsfc.nasa.gov/planetary/factsheet/sunfact.html
    // The Sun's distance from its own centre is zero.
    public static final CelestialBody SUN = new CelestialBody(
            "Sun", 1.9884e30, 695700.0, 0.0,
            "The star at the centre of our solar system.");

    // Source: https://nssdc.gsfc.nasa.gov/planetary/factsheet/earthfact.html
    // Use the orbital semimajor axis as Earth's average distance from the Sun.
    // NASA lists 149.598 million km, converted here to km.
    public static final CelestialBody EARTH = new CelestialBody(
            "Earth", 5.9722e24, 6371.0, 149598000.0,
            "Our home planet, the third planet from the Sun.");
}
