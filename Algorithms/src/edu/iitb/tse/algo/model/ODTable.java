package edu.iitb.tse.algo.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dtripathy10
 */
public class ODTable {

    private List<OD> odTable;

    public ODTable() {
        odTable = new ArrayList();
    }
    public void addOD(OD od) {
        odTable.add(od);
    }

    public List<OD> getOdTable() {
        return odTable;
    }

}
