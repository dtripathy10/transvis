package edu.iitb.tse.transvis.csvfile;

import edu.iitb.civil.tse.csv.table.model.Reading;
import edu.iitb.civil.tse.csv.table.ui.TableTopComponent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author dtripathy10
 */
public class OpenSupport implements OpenInterface {

    private CSVDataObject csv = null;

    public OpenSupport(CSVDataObject csv) {
        this.csv = csv;
    }

    @Override
    public void open1() {
        FileObject f = csv.getPrimaryFile();
        Reading reader = new Reading(FileUtil.toFile(f));
        String displayName = FileUtil.getFileDisplayName(f);
        TableTopComponent tabletopComponent = new TableTopComponent(reader.getTableData(), reader.getTableHeader(), reader.getType());
        tabletopComponent.open();
        tabletopComponent.setDisplayName(f.getName()+"."+f.getExt());
        tabletopComponent.requestActive();
    }


}
