package edu.iitb.tse.transvis.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author dtripathy10
 */
@ServiceProvider(service=ProjectFactory.class)
public class TRANSVISProjectFactory implements ProjectFactory {

    public static final String PROJECT_DIR = "transvisproject";
    public static final String PROJECT_PROPFILE = "project.properties";

    @Override
    public boolean isProject(FileObject projectDirectory) {
        return projectDirectory.getFileObject(PROJECT_DIR) != null;
    }

    @Override
    public Project loadProject(FileObject projectDirectory, ProjectState state) throws IOException {
        return isProject(projectDirectory) ? new TRANSVISProject(projectDirectory, state) : null;
    }

    @Override
    public void saveProject(Project project) throws IOException, ClassCastException {
        FileObject projectRoot = project.getProjectDirectory();
        if (projectRoot.getFileObject(PROJECT_DIR) == null) {
            throw new IOException("Project dir " + projectRoot.getPath() + " deleted,"
                    + " cannot save project");
        }

        //Force creation of the input/ dir if it was deleted
        ((TRANSVISProject) project).getInputFolder(true);

        //Find the properties file pvproject/project.properties,
        //creating it if necessary
        String propsPath = PROJECT_DIR + "/" + PROJECT_PROPFILE;
        FileObject propertiesFile = projectRoot.getFileObject(propsPath);
        if (propertiesFile == null) {
            //Recreate the properties file if needed
            propertiesFile = projectRoot.createData(propsPath);
        }

        Properties properties = project.getLookup().lookup(Properties.class);
        File f = FileUtil.toFile(propertiesFile);
        properties.store(new FileOutputStream(f), "TRANSVIS Project Properties");
    }
}
