/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.civil.tse.csv.table.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.swingx.JXLabel;

public class ColorRenderer extends JXLabel
        implements TableCellRenderer {

    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;

    public ColorRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        setOpaque(true); //MUST do this for background to show up.
    }

   
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          String newColor = (String) value;
        setHorizontalAlignment(SwingConstants.CENTER);
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) {
                    selectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                            table.getSelectionBackground());
                }
                setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5,
                            table.getBackground());
                }
                setBorder(unselectedBorder);
            }
        }
       // setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.WHITE));
       // setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(10,10));
        setText(newColor);
        return this;
    }
}
