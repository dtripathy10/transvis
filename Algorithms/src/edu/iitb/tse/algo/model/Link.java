package edu.iitb.tse.algo.model;

import java.awt.Color;
import java.util.List;

/**
 *
 * @author dtripathy10
 */
public class Link {

    private String id;
    private String information;
    private Node source;
    private Node destination;
    private double freeFlowTravelTime;
    private double volume;
    private Color color = Color.BLACK;//Color.decode("#FF5050");//default color of edge
    private double capacity;
    private List<Lane> lanes;
    public boolean paralla;



    public Link(String id, String information, Node source, Node destination) {
        this.id = id;
        this.information = information;
        this.source = source;
        this.destination = destination;
    }

    public Link(String id, Node source, Node destination, double freeFlowTravelTime) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.freeFlowTravelTime = freeFlowTravelTime;
    }

    public Link(String id, String information, Node source, Node destination, double freeFlowTravelTime, double capacity) {
        this.id = id;
        this.information = information;
        this.source = source;
        this.destination = destination;
        this.freeFlowTravelTime = freeFlowTravelTime;
        this.capacity = capacity;
    }

    public Link(String id, Node source, Node destination, double freeFlowTravelTime, double capacity) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.freeFlowTravelTime = freeFlowTravelTime;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public double getFreeFlowTravelTime() {
        return freeFlowTravelTime;
    }

    public void setFreeFlowTravelTime(double freeFlowTravelTime) {
        this.freeFlowTravelTime = freeFlowTravelTime;
    }

    public String getInformation() {
        return information;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public double getVolume() {
        return volume;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Link{" + "id=\t" + id + ", information=\t" + information + ", source node=\t" + source.getId() + ", destination node=\t" + destination.getId() + ", freeFlowTravelTime=\t" + freeFlowTravelTime + ", volume=\t" + volume + ", capacity=\t" + capacity + '}';
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
