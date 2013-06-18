package edu.iitb.tse.transvis.csvfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "File",
id = "edu.iitb.tse.transvis.csvfile.Open")
@ActionRegistration(
    displayName = "#CTL_Open")
@ActionReference(path = "Loaders/text/x-mycsv/Actions", position = 0)
@Messages("CTL_Open=Open in matrix form")
public final class Open implements ActionListener {

    private final CSVDataObject context;

    public Open(CSVDataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        OpenInterface oi= context.getLookup().lookup(OpenInterface.class);
        oi.open1();
    }
}
