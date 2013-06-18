/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.tse.transvis.experimentui.toolbar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import org.openide.awt.*;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.util.actions.Presenter;


@ActionID(category = "File",
id = "edu.iitb.civil.tse.ui.tripdistribution.patoodConverter.MyDropDownButton")
@ActionRegistration(lazy = false,
displayName = "#CTL_MyDropDownButton")
@ActionReferences({
    @ActionReference(path = "Toolbars/MyToolbar", position = 3533)
})
@Messages("CTL_MyDropDownButton=MyDropDownButton")
public final class MyDropDownButton extends AbstractAction implements Presenter.Toolbar {
    final String EXTENSION_POINT = "MyDropDownActions";
    JPopupMenu popupp = new JPopupMenu();
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Component getToolbarPresenter() {
        for(Action a:Utilities.actionsForPath(EXTENSION_POINT)) {
            popupp.add(a);
        }
        return DropDownButtonFactory.createDropDownButton(
                ImageUtilities.loadImageIcon("edu/iit/tse/transvis/experimentui/toolbar/run.png", false),popupp);
    }

}
