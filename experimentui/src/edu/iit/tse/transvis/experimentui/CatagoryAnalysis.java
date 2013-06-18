/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui;

import edu.iit.tse.transvis.experimentui.tripgeneration.CategoryDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

@ActionID(
    category = "Edit",
id = "edu.iit.tse.transvis.experimentui.CatagoryAnalysis")
@ActionRegistration(
        //iconBase = "edu/iit/tse/transvis/experimentui/systemoptimal.png",
    displayName = "#CTL_CatagoryAnalysis")
@ActionReference(path = "Menu/Experiment/TripGeneration", position = 3358)
@Messages("CTL_CatagoryAnalysis=Catagory Analysis")
public final class CatagoryAnalysis implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryDialog d = new CategoryDialog(WindowManager.getDefault().getMainWindow(), true);
        d.setTitle("Category Analysis");
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }
}
