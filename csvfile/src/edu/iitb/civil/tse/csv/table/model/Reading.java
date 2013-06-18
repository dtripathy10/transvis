/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.civil.tse.csv.table.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author debabrata
 */
public class Reading {

    File fileName;
    String type;
    Integer NumberOfHeader;
    String[] headerType;
    String[] headerName;
    List<String[]> table = new ArrayList();
    BufferedReader bufRdr = null;
    String line = null;

    public Reading(File fileName) {
        this.fileName = fileName;
        storeHeaderInformation();
    }

    private void storeHeaderInformation() {
        try {
            bufRdr = new BufferedReader(new FileReader(fileName));
            while ((line = bufRdr.readLine()) != null) {
                //Check the header informataion.
                if (line.trim().startsWith("#")) {
                    String[] parts = line.trim().split("=");
                    String beforeEqual = parts[0].trim();
                    String afterEqual = parts[1].trim();
                    if (beforeEqual.equals("#type")) {
                        type = afterEqual;
                    }
                    if (beforeEqual.equals("#header")) {
                        NumberOfHeader = new Integer(afterEqual);
                    }
                    if (beforeEqual.equals("#header_type")) {
                        headerType = afterEqual.trim().split(",");
                    }
                    if (beforeEqual.equals("#header_name")) {
                        headerName = afterEqual.trim().split(",");
                    }
                } else {
                    if (type.equals("table")) {
                        //read the all the data and store it into a array
                        readTable();
                        break;
                    }
                    if (type.equals("matrix")) {
                        //read the all the data and store it into a array
                        readMatrix();
                        break;
                    }
                }
            }
            //close the file
            bufRdr.close();
        } catch (Exception ex) {
        }
    }

    private void readTable() {
        try {
            String[] parts = line.trim().split(",");
            table.add(parts);
            while ((line = bufRdr.readLine()) != null) {
                parts = line.trim().split(",");
                table.add(parts);
            }
        } catch (IOException ex) {
            Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String[]> getTableData() {
        return table;
    }

    public String[] getTableHeader() {
        return headerName;
    }

    private void readMatrix() {
        try {
            String[] parts = line.trim().split(",");
            table.add(parts);
            while ((line = bufRdr.readLine()) != null) {
                parts = line.trim().split(",");
                table.add(parts);
            }
        } catch (IOException ex) {
            Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getType() {
        return type;
    }
}
