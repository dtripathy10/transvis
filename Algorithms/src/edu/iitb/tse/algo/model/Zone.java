package edu.iitb.tse.algo.model;

import java.util.List;

/**
 *
 * @author dtripathy10
 */
public class Zone {

    public static final String TYPE1 = "TYPE1";
    public static final String TYPE2 = "TYPE2";
    public static final String TYPE3 = "TYPE3";
    public static final String TYPE4 = "TYPE4";
    public static final String TYPE5 = "TYPE5";
    private String zoneId;
    private List<Node> presentedNodes;
    private List<Link> presentedLinks;
    private int type;
}
