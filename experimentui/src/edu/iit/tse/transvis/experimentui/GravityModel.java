/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "Edit",
id = "edu.iit.tse.transvis.experimentui.GravityModel")
@ActionRegistration(
    displayName = "#CTL_GravityModel")
@ActionReference(path = "Menu/Experiment/TripDistribution", position = 3333, separatorAfter = 3383)
@Messages("CTL_GravityModel=Gravity Model")
public final class GravityModel implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
