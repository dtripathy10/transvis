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
id = "edu.iit.tse.transvis.experimentui.Final")
@ActionRegistration(
    iconBase = "edu/iit/tse/transvis/experimentui/help.png",
displayName = "#CTL_Final")
@ActionReference(path = "Toolbars/MyToolbar", position = 3633)
@Messages("CTL_Final=Help")
public final class Final implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
