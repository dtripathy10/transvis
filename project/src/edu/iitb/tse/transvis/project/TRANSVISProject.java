package edu.iitb.tse.transvis.project;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.ProjectState;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author dtripathy10
 */
public final class TRANSVISProject implements Project {

    public static final String INPUT_DIR = "input"; //NOI18N
    public static final String IMAGES_DIR = "images"; //NOI18N
    public static final String KEY_MAINFILE = "main.file";
    private final FileObject projectDir;
    LogicalViewProvider logicalView = new TRANSVISLogicalView(this);
    private final ProjectState state;

    public TRANSVISProject(FileObject projectDir, ProjectState state) {
        this.projectDir = projectDir;
        this.state = state;
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    FileObject getInputFolder(boolean create) {
        FileObject result =
                projectDir.getFileObject(INPUT_DIR);

        if (result == null && create) {
            try {
                result = projectDir.createFolder(INPUT_DIR);
            } catch (IOException ioe) {
                Exceptions.printStackTrace(ioe);
            }
        }
        return result;
    }

    FileObject getImagesFolder(boolean create) {
        FileObject result =
                projectDir.getFileObject(IMAGES_DIR);
        if (result == null && create) {
            try {
                result = projectDir.createFolder(IMAGES_DIR);
            } catch (IOException ioe) {
                Exceptions.printStackTrace(ioe);
            }
        }
        return result;
    }
    private Lookup lkp;

    @Override
    public Lookup getLookup() {
    if (lkp == null) {
        lkp = Lookups.fixed(new Object[] {
            this,  //handy to expose a project in its own lookup
            state, //allow outside code to mark the project as needing saving
            new ActionProviderImpl(), //Provides standard actions like Build and Clean
            loadProperties(), //The project properties
            new Info(), //Project information implementation
            logicalView, //Logical view of project implementation
            new MainFileProviderImpl(this), //So things can set the main file
            new RendererServiceImpl(this), //Renderer Service Implementation
        });
    }
    return lkp;
}

    private Properties loadProperties() {

        FileObject fob = projectDir.getFileObject(TRANSVISProjectFactory.PROJECT_DIR
                + "/" + TRANSVISProjectFactory.PROJECT_PROPFILE);

        Properties properties = new NotifyProperties(state);
        if (fob != null) {
            try {
                properties.load(fob.getInputStream());
            } catch (Exception e) {
                Exceptions.printStackTrace(e);
            }
        }

        return properties;

    }

    private static class NotifyProperties extends Properties {

        private final ProjectState state;

        NotifyProperties(ProjectState state) {
            this.state = state;
        }

        @Override
        public Object put(Object key, Object val) {

            Object result = super.put(key, val);

            if (((result == null) != (val == null)) || (result != null
                    && val != null && !val.equals(result))) {
                state.markModified();
            }

            return result;

        }
    }

    private final class ActionProviderImpl implements ActionProvider {

        @Override
        public String[] getSupportedActions() {
            return new String[0];
        }

        @Override
        public void invokeAction(String string, Lookup lookup) throws IllegalArgumentException {
            //do nothing
        }

        @Override
        public boolean isActionEnabled(String string, Lookup lookup) throws IllegalArgumentException {
            return false;
        }
    }

    /**
     * Implementation of project system's ProjectInformation class
     */
    private final class Info implements ProjectInformation {

        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(
                    "edu/iitb/tse/transvis/project/resources/project.png"));
        }

        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public Project getProject() {
            return TRANSVISProject.this;
        }
    }
}
