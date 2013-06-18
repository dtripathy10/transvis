/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JSeparator;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;

@ActionID(
    category = "Edit",
id = "edu.iit.tse.transvis.experimentui.Separator1")
@ActionRegistration(
       lazy = false,
displayName = "#CTL_Separator1")
@ActionReference(path = "Toolbars/MyToolbar", position = 3383)
@Messages("CTL_Separator1=Separator1")
public final class Separator1 extends AbstractAction implements Presenter.Toolbar {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }

    @Override
    public Component getToolbarPresenter() {
        JSeparator separator = new JSeparator();
        separator.setOrientation(JSeparator.VERTICAL);
        return separator;
    }
}
