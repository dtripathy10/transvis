package edu.iitb.tse.algo.model;

import java.awt.Color;

/**
 *
 * @author dtripathy10
 */
public class Node {

    private String id;
    private String information;
    private double xCordinate;
    private double yCordinate;
    private double zCordinate;
    private int radius = 9;
    private Color color = Color.decode("#000000");
    public static int DEFAULTRADIUS = 5;

    public Node(String id, String information) {
        this.id = id;
        this.information = information;
    }

    public double getzCordinate() {
        return zCordinate;
    }

    public void setzCordinate(double zCordinate) {
        this.zCordinate = zCordinate;
    }

    public Node(String id) {
        this.id = id;
    }

    public Node(String id, double xCordinate, double yCordinate) {
        this.id = id;
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

    public Node(String id, String information, double xCordinate, double yCordinate) {
        this.id = id;
        this.information = information;
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public String getId() {
        return id;
    }

    public String getInformation() {
        return information;
    }

    public double getxCordinate() {
        return xCordinate;
    }

    public double getyCordinate() {
        return yCordinate;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setxCordinate(double xCordinate) {
        this.xCordinate = xCordinate;
    }

    public void setyCordinate(double yCordinate) {
        this.yCordinate = yCordinate;
    }

    @Override
    public String toString() {
        return "Node{" + "id=\t" + id + ", information=\t" + information + ", xCordinate=\t" + xCordinate + ", yCordinate=\t" + yCordinate + '}';
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
