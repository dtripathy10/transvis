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

@Messages(value = "CTL_TestToolBar=Help")
@ActionID(id = "edu.iit.tse.transvis.experimentui.HelpToolBar", category = "Edit")
@ActionRegistration(lazy = false, displayName = "#CTL_TestToolBar")
@ActionReference(path = "Toolbars/MyToolbar", position = 3533)
public final class HelpToolBar extends AbstractAction implements Presenter.Toolbar {

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
