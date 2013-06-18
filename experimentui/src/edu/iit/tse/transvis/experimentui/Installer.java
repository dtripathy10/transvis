package edu.iit.tse.transvis.experimentui;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.openide.loaders.DataObject;
import org.openide.modules.ModuleInstall;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

public class Installer extends ModuleInstall {

    private static Lookup.Result<DataObject> dtoResult;
    private static Lookup.Result<Project> prjResult;
    public static List<Project> projects = new ArrayList();

    @Override
    public void restored() {
        prjResult = Utilities.actionsGlobalContext().lookupResult(Project.class);
        prjResult.addLookupListener(new LookupListener() {
            @Override
            public void resultChanged(LookupEvent ev) {
                for (Project project : prjResult.allInstances()) {
                    System.out.println("11111Project " + project.getProjectDirectory());
                }
            }
        });
        dtoResult = Utilities.actionsGlobalContext().lookupResult(DataObject.class);
        dtoResult.addLookupListener(new LookupListener() {

            @Override
            public void resultChanged(LookupEvent ev) {
                projects = new ArrayList();
                for (DataObject dto : dtoResult.allInstances()) {
                    Project project = FileOwnerQuery.getOwner(dto.getPrimaryFile());
                    System.out.println(project.getProjectDirectory().getName());
                    ProjectInformation.setCurrentProject(project);
                    if (project != null) {
                        //projects.add(project);
                        //ProjectInformation.setCurrentProject(project);
                        //System.out.println("File " + dto.getPrimaryFile() + " of Project " + project.getProjectDirectory());
                    }
                }
            }
        });
    }
}
