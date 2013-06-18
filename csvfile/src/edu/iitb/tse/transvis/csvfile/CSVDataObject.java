/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iitb.tse.transvis.csvfile;

import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;

@Messages({
    "LBL_CSV_LOADER=Files of CSV"
})
@MIMEResolver.ExtensionRegistration(
    displayName = "#LBL_CSV_LOADER",
mimeType = "text/x-mycsv",
extension = {"csv"})
@DataObject.Registration(
    mimeType = "text/x-mycsv",
iconBase = "edu/iitb/tse/transvis/csvfile/csv.png",
displayName = "#LBL_CSV_LOADER",
position = 300)
@ActionReferences({
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
    position = 100,
    separatorAfter = 200),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
    position = 300),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
    position = 400,
    separatorAfter = 500),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
    position = 600),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
    position = 700,
    separatorAfter = 800),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
    position = 900,
    separatorAfter = 1000),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
    position = 1100,
    separatorAfter = 1200),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
    position = 1300),
    @ActionReference(
        path = "Loaders/text/x-mycsv/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
    position = 1400)
})
public class CSVDataObject extends MultiDataObject {

    public CSVDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-mycsv", false);
        getCookieSet().assign(OpenInterface.class, new OpenSupport(this));
    }

    @Override
    protected int associateLookup() {
        return 1;
    }
    protected Node creNodeDelegated() {
        return new DataNode(this, Children.LEAF, getLookup());
    }
    @Override
    public Lookup getLookup() {
        return getCookieSet().getLookup();
    }
}
