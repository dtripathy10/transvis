
package edu.iitb.tse.transvis.api;

import org.openide.filesystems.FileObject;



/**
 *
 * @author dtripathy10
 */
public abstract class MainFileProvider {

    public abstract FileObject getMainFile();

    public abstract void setMainFile(FileObject file);

    public boolean isMainFile(FileObject file) {
        return file.equals(getMainFile());
    }
}
