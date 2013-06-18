/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui;

import edu.iit.tse.transvis.experimentui.tripgeneration.RegressionDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

@ActionID(
    category = "Edit",
id = "edu.iit.tse.transvis.experimentui.RegressionAnalysis")
@ActionRegistration(
       // iconBase = "edu/iit/tse/transvis/experimentui/systemoptimal.png",
    displayName = "#CTL_RegressionAnalysis")
@ActionReference(path = "Menu/Experiment/TripGeneration", position = 3333, separatorAfter = 3383)
@Messages("CTL_RegressionAnalysis=Regression Analysis")
public final class RegressionAnalysis implements ActionListener {

    RegressionDialog d;

    @Override
    public void actionPerformed(ActionEvent e) {

        //preprocesing the data ((checking for data is valid or not)
        //run the experiment
        //post processing the data
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                d = new RegressionDialog(WindowManager.getDefault().getMainWindow(), true);
                d.setTitle("Regression Analysis | Tripa Generation");
                d.setLocationRelativeTo(null);
                d.setVisible(true);
            }
        });
    }
}
