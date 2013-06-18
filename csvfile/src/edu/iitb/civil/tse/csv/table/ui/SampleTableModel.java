package edu.iitb.civil.tse.csv.table.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * SampleTableModel
 */
public class SampleTableModel extends AbstractTableModel {

    private List<String[]> table;
    private String[] names;
    private int totalNumberOfRow;
    public String type;

    public SampleTableModel(List<String[]> table, String[] names, String type) {
        this.table = table;
        this.names = names;
        this.type = type;
        System.out.println(type.equals("matrix"));
        if (type.equals("table")) {
            //change the column name
            add("rowNumber");
            //Add One column to every row
            add();
        }
        if (type.equals("matrix")) {
            //change the column name
            add("   ");
            //Add One column to every row
           create();
        }
    }

    public double getTickUnit() {
        return 1000;
    }

    public String[] getColumnNames() {
        return names;
    }

    @Override
    public int getRowCount() {
        return table.size();
    }

    @Override
    public int getColumnCount() {
        return names.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (table.get(row).length < table.get(0).length) {
            if (table.get(row).length < column + 1) {
                return "#undefine";
            } else {
                return table.get(row)[column];
            }
        }
        return table.get(row)[column];

    }

    @Override
    public String getColumnName(int column) {
        return names[column];
    }

    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0) {
            return false;
        }
        if (type.equals("matrix")) {
            if (column == 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        table.get(row)[column] = (String) value;
        fireTableCellUpdated(row, column);
    }

    private void add(String rowNumber) {
        String[] temp = new String[names.length + 1];
        temp[0] = rowNumber;
        System.arraycopy(names, 0, temp, 1, names.length);
        names = temp;
    }

    private void add() {
        int count = 0;
        List<String[]> tableTemp = new ArrayList<String[]>();
        for (String[] strings : table) {
            count++;
            String[] temp = new String[strings.length + 1];
            temp[0] = "" + (count) + "";
            System.arraycopy(strings, 0, temp, 1, strings.length);
            tableTemp.add(temp);
        }
        table = tableTemp;
    }

    private void create() {
        int count = 0;
        List<String[]> tableTemp = new ArrayList<String[]>();

        for (String[] strings : table) {
            String[] temp = new String[strings.length + 1];
            temp[0] = names[count + 1];
            System.arraycopy(strings, 0, temp, 1, strings.length);
            tableTemp.add(temp);
            count++;
        }
        table = tableTemp;
    }
}
